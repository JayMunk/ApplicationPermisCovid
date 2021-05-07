import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Citoyen } from 'src/app/models/citoyen';
import { PermisService } from 'src/app/services/permis.service';

@Component({
  selector: 'app-get-permis',
  templateUrl: './get-permis.component.html',
  styleUrls: ['./get-permis.component.css']
})
export class GetPermisComponent implements OnInit {

  constructor(private service: PermisService, private router: Router) {

  }

  citoyen: Citoyen;
  validMessage: string = '';
  enfants: Citoyen[];
  emailSent: boolean;
  pdf: File;

  getPermisForm = new FormGroup({
    citoyen: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required)
  });

  ngOnInit(): void {
    this.citoyen = JSON.parse(sessionStorage.getItem("citoyen"));
    this.enfants = JSON.parse(sessionStorage.getItem("enfants"));
    let btnEmail = document.getElementById("sendEmail");
    btnEmail.addEventListener("click", (e: Event) => this.sendEmail());
    let btnPDF = document.getElementById("openPDF");
    btnPDF.addEventListener("click", (e: Event) => this.openPDF());
  }

  sendEmail() {
    if (this.getPermisForm.valid) {
      this.service.sendEmail(this.getPermisForm.get("citoyen").value, this.getPermisForm.get("email").value).subscribe((data) => {
        this.emailSent = data
        if (this.emailSent) {
          this.validMessage = 'Vérifier vos courriel';
        } else {
          this.validMessage = 'Erreur';
        }
      });
    } else {
      this.validMessage = 'Please fill the form before submitting!';
    }

  }
  openPDF() {
    if (this.getPermisForm.valid) {
      this.service.getPDF(this.getPermisForm.get("citoyen").value).subscribe((data) => {
        this.pdf = data;
        sessionStorage.setItem("pdf", JSON.stringify(this.pdf));
        window.open(sessionStorage.getItem("pdf"));
        this.validMessage = 'Le PDF est ouvert dans un nouvel onglet';
      });
    } else {
      this.validMessage = 'Please fill the form before submitting!';
    }
  }

}
