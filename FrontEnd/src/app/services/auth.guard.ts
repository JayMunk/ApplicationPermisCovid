import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router, CanDeactivate } from '@angular/router';
import { Observable } from 'rxjs';
import { PermisService } from './permis.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private service: PermisService) { }


  canActivate(
    route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (!this.service.userIsLogin()) {
      this.router.navigateByUrl('/login')
      return true;
    } else {
      return false;
    }
  }

}
