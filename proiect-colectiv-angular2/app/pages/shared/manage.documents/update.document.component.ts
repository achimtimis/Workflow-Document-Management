import { Component } from '@angular/core';
import { Router, Params, ActivatedRoute } from '@angular/router';

import { User, UserService } from '../../../users/index'
import { Document, DocumentService } from '../../../documents/index';
import { AlertService} from '../../../core/index';

@Component({
  moduleId: module.id,
  templateUrl: 'update.document.html'
})

export class UpdateDocumentComponent {
  model: any = {};
  loading = false;
  user: User ;
  document: Document;
  currentUser : User = JSON.parse(localStorage.getItem('currentUser'));

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private documentService: DocumentService,
    private alertService: AlertService) {
    
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
        let documentId = params['id'];
        this.documentService.getById(documentId).subscribe(document => { this.document = document; 
          this.model = document;});
      });
  }
  updateDocument() {
    this.loading = true;
    this.documentService.update(this.model.id, this.model)
      .subscribe(
      data => {
        this.alertService.success('Document updated', true);
        this.router.navigate(['/documents/manage']);
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }
  private loadDetails() {

  }
}
