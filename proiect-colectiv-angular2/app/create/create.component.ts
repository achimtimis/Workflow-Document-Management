import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AlertService, UserService } from '../_services/index';

@Component({
    moduleId: module.id,
    templateUrl: 'create.component.html'
})

export class CreateComponent {
    model: any = {};
    loading = false;

    constructor(
        private router: Router,
        private userService: UserService,
        private alertService: AlertService) { }
        
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
