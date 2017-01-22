import { Component, OnInit } from '@angular/core';

import { AlertService } from '../../../core/index';
import { DocumentFlux } from '../../../document.flux'
import { User, UserService } from '../../../users/index';
import { Group } from '../../../groups/group';
import { Document, DocumentService } from '../../../documents/index';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs'
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';

@Component({
  moduleId: module.id,
  templateUrl: 'manage.flux.html'
})

export class ManageFluxComponent implements OnInit {

  currentUser: User;
  documents: Observable<Document[]>;
  users: Observable<User[]>;
  groups: Observable<Group[]>;
  fluxDocs: Observable<Document[]>;
  document : Document;
  private selectedDocumentId: number;
  private selectedUserId: number;
  private selectedGroupId: number;
  loading = false;
  showStyle = false;

  constructor(private documentService: DocumentService, private alertService: AlertService,
    private userService: UserService, private route: ActivatedRoute, private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.loadAllDocuments();
    this.loadAllFluxes();
  }

  viewDocument(document: Document) {
    this.router.navigate(['/documents/view', document.id]);
  }

  private loadAllUsers() {
    this.users = this.route.params
      .switchMap((params: Params) => {
        this.selectedUserId = +params['id'];
        return this.userService.getAll();
      });
  }

  private loadAllGroups() {
    this.groups = this.route.params
      .switchMap((params: Params) => {
        this.selectedGroupId = +params['id'];
        return this.userService.getAllGroups();
      });
  }

  private loadAllDocuments() {
    if (this.currentUser.role == 'READER') {
      this.documents = this.route.params
        .switchMap((params: Params) => {
          return this.documentService.getAll();
        });
    } else {
      this.documents = this.route.params
        .switchMap((params: Params) => {
          return this.documentService.getById(this.currentUser.id);
        });
    }
  }

  private loadAllFluxes() {
    this.fluxDocs = this.route.params
      .switchMap((params: Params) => {
        return this.documentService.getAllDocumentFluxForId(this.currentUser.id);
      });
  }

  deleteDocument(id: number) {
    this.documentService.delete(id).subscribe(() => { this.loadAllDocuments() });
  }
  isSelectedDocument(document: Document) { return true; }

  updateDocument(document: Document) {
    this.router.navigate(['/documents/update', document.id]);
  }

  denyDocument(id: number) {
   
    this.documentService.getDocumentById(id).subscribe(document => { this.document = document;
    this.documentService.denyDocument(this.currentUser, this.document)
      .subscribe(
      data => {
        this.alertService.success('Document Denied', true);
        this.loadAllDocuments();
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      });});
    
  }


  acceptDocument(id: number) {
    this.documentService.getDocumentById(id).subscribe(document => { this.document = document;this.documentService.acceptDocument(this.currentUser, this.document)
      .subscribe(
      data => {
        this.alertService.success('Document Accepted', true);
        this.loadAllDocuments();
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      }); });
    
  }
}