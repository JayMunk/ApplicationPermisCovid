import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Citoyen } from 'src/app/models/citoyen';
import { PermisService } from 'src/app/services/permis.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  citoyen: Citoyen
  enfantsTable: Citoyen[]
  nbEnfant: number

  constructor(private service: PermisService, private router: Router) { }

  ngOnInit(): void {
    this.citoyen = JSON.parse(sessionStorage.getItem('citoyen'));
    this.service.findEnfantsByTuteurEmail(this.citoyen.email).subscribe(
      (enfants) => {
        this.enfantsTable = enfants;
        this.nbEnfant = enfants.length;
        console.log(this.nbEnfant)
      }
    );
  }

}
