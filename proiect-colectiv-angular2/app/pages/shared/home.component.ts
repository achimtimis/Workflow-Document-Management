import { Component, OnInit } from '@angular/core';

import { User } from '../../users/index';
import { UserService } from '../../users/index';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs'
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';

@Component({
  moduleId: module.id,
  templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {

  currentUser: User;
  users: Observable<User[]>;
  private selectedId: number;

  constructor(private userService: UserService, private route: ActivatedRoute,
    private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.loadAllUsers();
  }

  deleteUser(id: number) {
    this.userService.delete(id).subscribe(() => { this.loadAllUsers() });
  }

  updateUser(user: User) {
    this.router.navigate(['/updateUser', user.id]);
  }
  private loadAllUsers() {
    this.users = this.route.params
      .switchMap((params: Params) => {
        this.selectedId = +params['id'];
        return this.userService.getAll();
      });
  }
  isSelected(user: User) { return user.id === this.selectedId; }
}