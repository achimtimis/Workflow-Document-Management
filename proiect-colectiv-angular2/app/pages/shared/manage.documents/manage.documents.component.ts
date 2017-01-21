import { Component, OnInit } from '@angular/core';

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
  fluxDocuments: Array<Document>;
  fluxGroups: Array<Group>;

  private selectedDocumentId: number;
  private selectedUserId: number;
  private selectedGroupId: number;
  loading = false;

  constructor(private documentService: DocumentService, private userService: UserService,
    private route: ActivatedRoute, private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.fluxDocuments = new Array<Document>();
    this.fluxGroups = new Array<Group>();
  }

  ngOnInit() {
    this.loadAllDocuments();
    this.loadAllUsers();
    this.loadAllGroups();
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
  isSelectedDocument(document: Document) { return document.id === this.selectedDocumentId; }

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

  private addDocumentToFlux(document: Document) {
    this.fluxDocuments.push(document);
  }

  private addGroupToFlux(group: Group) {
    this.fluxGroups.push(group);
  }

  private finalizeFlux() {
    this.loading = true;
    return this.documentService.addFlux(this.fluxDocuments, this.fluxGroups);
  }
}