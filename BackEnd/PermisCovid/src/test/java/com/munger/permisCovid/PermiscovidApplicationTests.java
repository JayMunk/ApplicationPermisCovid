package com.munger.permisCovid;

import com.munger.permisCovid.model.Citoyen;
import com.munger.permisCovid.model.Permis;
import com.munger.permisCovid.repository.CitoyenRepository;
import com.munger.permisCovid.repository.PermisRepository;
import com.munger.permisCovid.service.SystemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ComponentScan(basePackages = {"com.munger.permisCovid.service"})
class PermiscovidApplicationTests {

    @Autowired
    private CitoyenRepository citoyenRepository;
    @Autowired
    private PermisRepository permisRepository;

    @Autowired
    private SystemService systemService;

    @BeforeAll
    public void insertData() {
        Citoyen c1 = new Citoyen("Rea", "Nessi", "nrea0@nbcnews.com", "qiAcnel6NN", "8240309933", "F", 14, "992-439-9911", "Sumberrejo");
        Citoyen c2 = new Citoyen("Wimes", "Rollins", "rwimes2@chronoengine.com", "zjZ2BuZB", "520510178", "m", 26, "315-507-9672", "Kathu");
        Citoyen c3 = new Citoyen("Baddam", "Floria", "fbaddam3@cmu.edu", "DdwNe7oDnh8", "4044973644", "m", 35, "502-207-9174", "Bokkos");
        Permis p1 = new Permis(1, true, false);
        Permis p2 = new Permis(2, false, true);
        Permis p3 = new Permis(3, true, true);
        citoyenRepository.saveAll(Arrays.asList(c1, c2, c3));
        permisRepository.saveAll(Arrays.asList(p1, p2, p3));
    }

    @Test
    void testFindAllCitoyen() {
        assertEquals(citoyenRepository.findAll().size(), 3);
    }

    @Test
    void testFindCitoyen() {
        assertNotNull(citoyenRepository.findCitoyenByEmail("rwimes2@chronoengine.com"));
    }

    @Test
    void testFindCitoyenByLogin() {
        assertNotNull(systemService.login("rwimes2@chronoengine.com", "zjZ2BuZB"));
    }

    @Test
    void testFindEmailExist() {
        assertTrue(systemService.isLoginExist("rwimes2@chronoengine.com"));
    }

    @Test
    void testCitoyenHasPermis() {
        assertTrue(systemService.citoyenHasPermis("rwimes2@chronoengine.com"));
    }

    @Test
    void testCitoyenHasPermisValide() {
        assertTrue(systemService.citoyenHasPermisValide("rwimes2@chronoengine.com"));
    }


    @Test
    void testFindAllPermis() {
        assertEquals(permisRepository.findAll().size(), 3);
    }

    @Test
    void testFindPermis() {
        assertNotNull(permisRepository.findPermisByIdCitoyen(1));
    }

    @Test
    void testFindPermisValid() {
        assertNotNull(permisRepository.findPermisByIdCitoyenAndIsValideTrue(1));
    }


    @Test
    void testGenerateQR() {
    }

    @Test
    void testSendEmail() {
    }

    @Test
    void testGeneratePDF() {
    }
}
