import { Component, OnInit } from '@angular/core';

import { AlertService } from '../../core/index';
import { DocumentFlux } from '../../document.flux'
import { User, UserService } from '../../users/index';
import { Group } from '../../groups/group';
import { Document, DocumentService } from '../../documents/index';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs'
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';

@Component({
  moduleId: module.id,
  templateUrl: 'manage.zones.html'
})

export class ManageZonesComponent implements OnInit {

  currentUser: User;
  documents: Observable<Document[]>;
  users: Observable<User[]>;
  groups: Observable<Group[]>;
  fluxes: Observable<DocumentFlux[]>;

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
    this.documents = this.route.params
      .switchMap((params: Params) => {
        return this.documentService.getAllDocumentFluxForId(this.currentUser.id);
      });
  }
}