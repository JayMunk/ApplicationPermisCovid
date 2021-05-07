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

  constructor(private service: PermisService, private router: Router) { }

  citoyen: Citoyen;
  validMessage: string = '';
  enfants: Citoyen[];

  getPermisForm = new FormGroup({
    citoyen: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required)
  });

  ngOnInit(): void {
    this.citoyen = JSON.parse(sessionStorage.getItem("citoyen"));
    this.enfants = JSON.parse(sessionStorage.getItem("enfants"));
  }

  onSubmit() {
    if (this.getPermisForm.valid) {
      //si permis valide
      //send email
      //navigate dashboard

      //  this.service.save(this.signInForm.value).subscribe((data) => {
      //    this.signInForm.reset();
      //    console.log('passe ici');
      //   this.router.navigateByUrl('/dashboard');
      this.validMessage = 'good';
      //  });
    } else {
      this.validMessage = 'Please fill the form before submitting!';
    }
  }

}
