import { Component, OnInit } from '@angular/core';

import { User, UserService  } from '../../../users/index';
import { Document, DocumentService } from '../../../documents/index';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs'
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';

@Component({
  moduleId: module.id,
  templateUrl: 'manage.documents.html'
})

export class ManageDocumentsComponent implements OnInit {

  currentUser: User;
  documents: Observable<Document[]>;
  private selectedId: number;

  constructor(private documentService: DocumentService, private route: ActivatedRoute,
    private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.loadAllDocuments();
  }

  deleteDocument(id: number) {
    this.documentService.delete(id).subscribe(() => { this.loadAllDocuments() });
  }

  updateDocument(document: Document) {
    this.router.navigate(['/admin/update/1']);
  }
  private loadAllDocuments() {
    this.documents = this.route.params
      .switchMap((params: Params) => {
        this.selectedId = +params['id'];
        return this.documentService.getAll();
      });
  }
  isSelected(user: User) { return user.id === this.selectedId; }
}