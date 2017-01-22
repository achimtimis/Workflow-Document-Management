import { User } from '../../users'
import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (!localStorage.getItem('currentUser')) {
      this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
      return false;
    }
    let roles = route.data["roles"] as Array<string>;
    let tempUser: User = JSON.parse(localStorage.getItem('currentUser'));
    let currentRole = tempUser.role;

    let exist: boolean;
    if (roles == null) {
      return true;
    } else {
      if (roles.length == 0) {
        return true;
      }
    }
    for (let entry of roles) {
      if (entry === currentRole) {
        exist = true;
      }
    }
    if (exist) {
      return true;
    }
    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }
}