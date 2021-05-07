import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Citoyen } from 'src/app/models/citoyen';
import { PermisService } from 'src/app/services/permis.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  validMessage: string = '';
  flag: boolean;
  citoyen: Citoyen;
  email: string;
  pwd: string;

  constructor(private service: PermisService, private router: Router) { }

  loginForm = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });


  ngOnInit(): void { }

  onSubmit() {
    this.email = this.loginForm.get('email').value;
    this.pwd = this.loginForm.get('password').value;
    if (this.loginForm.valid) {
      this.service.login(this.email, this.pwd)
        .subscribe((data) => {
          this.citoyen = data;
          if (this.citoyen != null) {
            sessionStorage.setItem('citoyen', JSON.stringify(this.citoyen));
            this.router.navigateByUrl('/dashboard');
            this.loginForm.reset();
          } else {
            this.validMessage = 'Login or password incorrect!';
          }
        }
        )
    } else {
      this.validMessage = 'Please fill the form before submitting!';
    }
  }
}
