import { Component, OnInit } from '@angular/core';
import { AlertService } from '../../../core/index';
import { User, UserService } from '../../../users/index';
import { Group } from '../../../groups/index';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs'
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';

@Component({
  moduleId: module.id,
  templateUrl: 'manage.groups.html'
})

export class ManageGroupsComponent implements OnInit {

  model: any = {};
  loading = false;
  user: User;
  private selectedId: number;
  groups: Observable<Group[]>;
  currentUser: User = JSON.parse(localStorage.getItem('currentUser'));

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private alertService: AlertService) {

  }
  ngOnInit() {
    this.loadAllGroups();
  }
  addGroup() {
    this.userService.getById(this.model.id).subscribe(user => {
      this.user = user;
      this.loading = true;
      this.userService.addGroup(this.model.name, this.user)
        .subscribe(
        data => {
          this.alertService.success('Success!', true);
          this.loadAllGroups();
          this.router.navigate(['/manageGroups']);
        },
        error => {
          this.loading = false;
          this.loadAllGroups();
        });
    });

  }

  private loadAllGroups() {
    this.groups = this.route.params
      .switchMap((params: Params) => {
        this.selectedId = +params['id'];
        return this.userService.getAllGroups();
      });
  }
  isSelected(group: Group) { return group.id === this.selectedId; }
}