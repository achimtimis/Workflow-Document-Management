import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { User, UserService } from '../../../users/index';
import { AlertService } from '../../../core/index';

@Component({
    moduleId: module.id,
    templateUrl: 'create.component.html'
})

export class CreateComponent {
  
    currentUser: User;
    model: any = {};
    loading = false;

    constructor(
        private router: Router,
        private userService: UserService,
        private alertService: AlertService) { 
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }
        
     createNewUser(){
       this.loading = true;
       this.userService.create(this.model)
            .subscribe(
                data => {
                    this.alertService.success('User created', true);
                    this.router.navigate(['']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
     }
}
