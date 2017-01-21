import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { User, UserService } from '../../users/index';
import { UserDocument, DocumentService } from '../../documents/index';
import { AlertService } from '../../core/index';

@Component({
    moduleId: module.id,
    templateUrl: 'create.document.component.html'
})

export class CreateDocumentComponent {
  
    currentUser: User;
    model: any = {};
    loading = false;

    constructor(
        private router: Router,
        private documentService: DocumentService,
        private alertService: AlertService) { 
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }
        
     createNewDocument(){
       this.loading = true;
       this.documentService.create(this.model,this.currentUser)
            .subscribe(
                data => {
                    this.alertService.success('Document Successfully Created', true);
                    this.router.navigate(['/documents/manage']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
     }
}
