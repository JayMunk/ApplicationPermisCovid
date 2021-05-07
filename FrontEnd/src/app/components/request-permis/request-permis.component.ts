import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Citoyen } from 'src/app/models/citoyen';
import { MinistereService } from 'src/app/services/ministere.service';
import { PermisService } from 'src/app/services/permis.service';

@Component({
  selector: 'app-request-permis',
  templateUrl: './request-permis.component.html',
  styleUrls: ['./request-permis.component.css']
})
export class RequestPermisComponent implements OnInit {

  constructor(private servicePermis: PermisService, private serviceMinistere: MinistereService, private router: Router) { }

  citoyen: Citoyen;
  validMessage: string = '';
  nassm: string;
  resultRequest: string;
  enfants: Citoyen[];

  requestPermisForm = new FormGroup({
    citoyen: new FormControl('', Validators.required)
  });

  ngOnInit(): void {
    this.citoyen = JSON.parse(sessionStorage.getItem("citoyen"));
    this.enfants = JSON.parse(sessionStorage.getItem("enfants"));
    console.log("allo " + this.citoyen.numAssuranceSocial);

  }

  onSubmit() {
    console.log(this.requestPermisForm.value);
    if (this.requestPermisForm.valid) {
      this.validMessage = '';
      //le numero dassurance doit provenir du input et non hard coder
      this.serviceMinistere.checkCitizenPermisValidity(this.citoyen.numAssuranceSocial).subscribe((data) => {
        this.resultRequest = data;
        console.log("type Permis = " + this.resultRequest);
        if(this.resultRequest== "Vaccinated"){
          this.servicePermis.createPermis(this.citoyen.idUser,this.resultRequest);
        }else if(this.resultRequest== "Tested"){
          this.servicePermis.createPermis(this.citoyen.idUser,this.resultRequest).subscribe((data) => {
            this.citoyen = data;});
        }else if(this.resultRequest== "None"){
          this.validMessage = 'Vous devez soit vous faire vaccinner ou tester';
        }
      })
    } else {
      this.validMessage = 'Please fill the form before submitting!';
    }
  }
}
