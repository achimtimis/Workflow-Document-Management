import { Component } from '@angular/core';
import { Router, Params, ActivatedRoute } from '@angular/router';

import { User, UserService } from '../../../users/index'
import { AlertService} from '../../../core/index';

@Component({
  moduleId: module.id,
  templateUrl: 'update.component.html'
})

export class UpdateComponent {
  model: any = {};
  loading = false;
  user: User;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private alertService: AlertService) {
    this.userService.getById(15).subscribe(user => { this.user = user; });
  }

  ngOnInit() {
    this.route.params
      // (+) converts string 'id' to a number
      .switchMap((params: Params) => this.userService.getById(+params['id']))
      .subscribe((user: User) => this.user = user);
  }
  updateUser() {
    this.loading = true;
    this.userService.update(this.model.id, this.model)
      .subscribe(
      data => {
        this.alertService.success('User updated', true);
        this.router.navigate(['']);
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }
  private loadDetails() {

  }
}
