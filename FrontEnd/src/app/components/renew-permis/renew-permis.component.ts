import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Citoyen } from 'src/app/models/citoyen';
import { MinistereService } from 'src/app/services/ministere.service';
import { PermisService } from 'src/app/services/permis.service';

@Component({
  selector: 'app-renew-permis',
  templateUrl: './renew-permis.component.html',
  styleUrls: ['./renew-permis.component.css']
})
export class RenewPermisComponent implements OnInit {

  constructor(private servicePermis: PermisService, private serviceMinistere: MinistereService, private router: Router) { }

  citoyen: Citoyen;
  validMessage: string = '';
  infoValide: boolean;

  renewPermisForm = new FormGroup({
    citoyen: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    numTelephone: new FormControl('', Validators.required),
    ville: new FormControl('', Validators.required),
  });

  ngOnInit(): void { this.citoyen = JSON.parse(sessionStorage.getItem('citoyen')); }

  onSubmit() {
    //citoyen = select option
    if (this.renewPermisForm.valid) {
      this.servicePermis.checkCitizenValidityForRenewing(this.renewPermisForm.get("email").value, this.renewPermisForm.get("numTelephone").value, this.renewPermisForm.get("ville").value)
        .subscribe((data) => {
          this.infoValide = data;
          console.log(this.infoValide);
          if (this.infoValide) {
            this.servicePermis.renewPermis(this.citoyen.idUser).subscribe((data) => {
              this.citoyen = data;
              this.router.navigateByUrl('/dashboard');
              this.validMessage = 'good';
            });
          }
        });
    } else {
      this.validMessage = 'Please fill the form before submitting!';
    }
  }
}
