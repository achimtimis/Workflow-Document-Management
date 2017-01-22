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
  templateUrl: 'manage.documents.html'
})

export class ManageDocumentsComponent implements OnInit {

  currentUser: User;
  documents: Observable<Document[]>;
  users: Observable<User[]>;
  groups: Observable<Group[]>;
  fluxes: Observable<DocumentFlux[]>;
  documente: Observable<Document[]>;
  fluxDocuments: Array<Document>;
  fluxGroups: Array<Group>;

  private selectedDocumentId: number;
  private selectedUserId: number;
  private selectedGroupId: number;
  loading = false;
  showStyle = false;

  constructor(private documentService: DocumentService, private alertService: AlertService,
    private userService: UserService, private route: ActivatedRoute, private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.fluxDocuments = new Array<Document>();
    this.fluxGroups = new Array<Group>();
  }

  ngOnInit() {
    this.loadAllDocuments();
    this.loadAllUsers();
    this.loadAllGroups();
    this.loadAllFluxes();
  }

  viewDocument(document: Document) {
    this.router.navigate(['/documents/view', document.id]);
  }
  deleteDocument(id: number) {
    this.documentService.delete(id).subscribe(() => { this.loadAllDocuments() });
  }

  updateDocument(document: Document) {
    this.router.navigate(['/documents/update', document.id]);
  }
  private loadAllDocuments() {
    this.documents = this.route.params
      .switchMap((params: Params) => {
        this.selectedDocumentId = +params['id'];
        return this.documentService.getAll();
      });
  }
  private loadAllDocumentse() {
    this.documente = this.route.params
      .switchMap((params: Params) => {
        this.selectedDocumentId = +params['id'];
        return this.documentService.getAll();
      });
  }
  isSelectedDocument(document: Document) { return true; }

  private loadAllUsers() {
    this.users = this.route.params
      .switchMap((params: Params) => {
        this.selectedUserId = +params['id'];
        return this.userService.getAll();
      });
  }
  isSelectedUser(user: User) { return user.id === this.selectedUserId; }

  private loadAllGroups() {
    this.groups = this.route.params
      .switchMap((params: Params) => {
        this.selectedGroupId = +params['id'];
        return this.userService.getAllGroups();
      });
  }
  isSelectedGroup(group: Group) { return group.id === this.selectedGroupId; }

  private loadAllFluxes() {
    this.fluxes = this.route.params
      .switchMap((params: Params) => {
        return this.documentService.getAllFluxes();
      });
  }

  private addDocumentToFlux(document: Document) {
    if (!this.containsDocument(document)) {
      this.fluxDocuments.push(document);
    }
  }

  private addGroupToFlux(group: Group) {
    if (!this.containsGroup(group)) {
      this.fluxGroups.push(group);
    }
  }

  containsDocument(document: Document) {
    for (var i = 0; i < this.fluxDocuments.length; i++) {
      if (this.fluxDocuments[i] === document) {
        return true;
      }
    }
    return false;
  }

  containsGroup(group: Group) {
    for (var i = 0; i < this.fluxGroups.length; i++) {
      if (this.fluxGroups[i] === group) {
        return true;
      }
    }
    return false;
  }

  private finalizeFlux() {
    this.loading = true;
    this.documentService.addFlux(this.fluxDocuments, this.fluxGroups)
      .subscribe(
      data => {
        this.alertService.success('Success', true);
        this.router.navigate(['/manageGroups']);
        this.fluxDocuments = [];
        this.fluxGroups = [];
        this.loadAllFluxes();
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
        this.fluxDocuments = [];
        this.fluxGroups = [];
        this.loadAllFluxes();
      });
  }
}