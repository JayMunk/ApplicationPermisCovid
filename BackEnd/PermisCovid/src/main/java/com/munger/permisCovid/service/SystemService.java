package com.munger.permisCovid.service;

import com.munger.permisCovid.model.Citoyen;
import com.munger.permisCovid.model.Permis;
import com.munger.permisCovid.repository.CitoyenRepository;
import com.munger.permisCovid.repository.PermisRepository;
import com.munger2.ministerews.service.MinistereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

@Service
public class SystemService {
    private static final String FILE_PATH = "C:/ecole(AL)/Session(6)/420-445-AL_PROGRAMMATION_DANS_UN_ENVIRONNEMENT_TRANSACTIONNEL/TP2/qr/permis";
    @Autowired
    private CitoyenRepository citoyenRepository;
    @Autowired
    private PermisRepository permisRepository;

    @Autowired(required = false)
    private JavaMailSender mailSender;

    //@Autowired
    // private Environment environment;

    //  @Autowired
    private MinistereService serviceMinistere;

    public Citoyen login(String email, String pwd) {
        return citoyenRepository.findCitoyenByEmailAndPassword(email, pwd);
    }

    public boolean isLoginExist(String email) {
        return citoyenRepository.findCitoyenByEmail(email) != null;
    }

    public boolean citoyenHasPermis(String email) {
        Citoyen c1 = citoyenRepository.findCitoyenByEmail(email);
        return permisRepository.findPermisByIdCitoyen(c1.getIdUser()) != null;
    }

    public boolean citoyenHasPermisValide(String email) {
        Citoyen c1 = citoyenRepository.findCitoyenByEmail(email);
        return permisRepository.findPermisByIdCitoyenAndIsValideTrue(c1.getIdUser()) != null;
    }

    public boolean checkCitoyenValidityToRenewPermis(String email, String numTelephone, String ville) {
        Citoyen c1 = citoyenRepository.findCitoyenByEmailAndNumTelephoneAndVille(email, numTelephone, ville);
        System.out.println("C1= "+c1);
        if (c1 != null) {
            Permis p1 = permisRepository.findPermisByIdCitoyenAndAndIsVaccinatedIsFalse(c1.getIdUser());
            System.out.println("p1= "+p1);
            if (p1 != null) {
                return true;
            }
        }
        return false;
    }

    public Citoyen renewPermis(int id){
        Citoyen citoyen = citoyenRepository.findCitoyenByIdUser(id);
        citoyen.getPermis().renouveler();
        return citoyen;
    }

    public Citoyen requestPermis(int id, String typePermis) {
        Citoyen citoyen = citoyenRepository.findCitoyenByIdUser(id);
        Permis p1;
        if (typePermis.equals("Vaccinated")) {
            p1 = new Permis(citoyen.getIdUser(), true, false);
            citoyen.setPermis(p1);
        } else if (typePermis.equals("Tested")) {
            p1 = new Permis(citoyen.getIdUser(), false, true);
            p1.setDateExpiration(LocalDate.now().plusDays(14));
            citoyen.setPermis(p1);
        }
        return citoyen;
    }

    public void generateQR(String data, int id) throws Exception {
        //   String filePath = "C:/ecole(AL)/Session(6)/420-445-AL_PROGRAMMATION_DANS_UN_ENVIRONNEMENT_TRANSACTIONNEL/TP2/qr/permis";
        Path path = FileSystems.getDefault().getPath(FILE_PATH + id + ".png");
        QRCodeWriter qr = new QRCodeWriter();
        MatrixToImageWriter.writeToPath(qr.encode(data, BarcodeFormat.QR_CODE,
                //Integer.parseInt(environment.getProperty("qrCode.with")),
                //Integer.parseInt(environment.getProperty("qrCode.height"))),
                //environment.getProperty("qrCode.extension"),
                300, 300), "PNG",
                path);
    }

    public void generatePDF(String prenom, int id) throws Exception {
        //   String filePath = "C:/ecole(AL)/Session(6)/420-445-AL_PROGRAMMATION_DANS_UN_ENVIRONNEMENT_TRANSACTIONNEL/TP2/qr/permis";
        PdfWriter writer = new PdfWriter(FILE_PATH + id + ".pdf");

        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf);
        Image image = new Image(ImageDataFactory.create(FILE_PATH + id + ".png"));

        Paragraph p = new Paragraph("Bonjour " + prenom + "\n")
                .add("Voici ton code permis de sante")
                .add(image);
        document.add(p);
        document.close();
    }

    public void sendEmail(String mailTo, String subject, String body, int id) throws Exception {
        //   String filePath = "C:/ecole(AL)/Session(6)/420-445-AL_PROGRAMMATION_DANS_UN_ENVIRONNEMENT_TRANSACTIONNEL/TP2/qr/permis";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(mailTo);
        helper.setSubject(subject);
        helper.setText(body);
        helper.addAttachment("QR CODE", new File(FILE_PATH + id + ".png"));
        helper.addAttachment("QR PDF", new File(FILE_PATH + id + ".pdf"));

        // helper.setCc();
    }

    public static void main(String[] args) throws Exception {
        // generateQR("HAMR123456;TEST;2021/03/31;5142223456", 1);
        // generatePDF("jeremie", 1);
    }

    public Citoyen createUser(Citoyen c) {
        return citoyenRepository.save(c);
    }

    public List<Citoyen> findChild(String email) {
        Citoyen c1 = citoyenRepository.findCitoyenByEmail(email);
        int idTuteur = c1.getIdUser();
        return citoyenRepository.findEnfantByIdTuteur(idTuteur);
    }
}