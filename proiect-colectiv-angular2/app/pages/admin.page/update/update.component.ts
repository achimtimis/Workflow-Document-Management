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
  user: User ;
  currentUser : User = JSON.parse(localStorage.getItem('currentUser'));

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private alertService: AlertService) {
    
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
        let userId = params['id'];
        this.userService.getById(userId).subscribe(user => { this.user = user; 
          this.model = user;});
      });
  }
  updateUser() {
    this.loading = true;
    this.userService.update(this.model.id, this.model)
      .subscribe(
      data => {
        this.alertService.success('User updated', true);
        this.router.navigate(['/admin/manage']);
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      });
  }
  private loadDetails() {

  }
}
