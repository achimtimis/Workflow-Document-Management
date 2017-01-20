import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { User } from './user';

let USERS = [ new User (1,'admins','pass','manager')];
let   hpromise = Promise.resolve(USERS);

@Injectable()
export class UserService {
  constructor(private http: Http) { }

  
  getUsers(){ return hpromise;}
  
  getUser(id:number | string){
    return hpromise.then(users=>users.find(user=>user.id==id));
  }
  //TODO: look into using observables i.e. getAll(): Observable<Response>
  getAll() {
    return this.http.get('http://localhost:8080/users',this.jwt()).map((response: Response) => response.json());
  }

  getById(id: number) {
    return this.http.get('http://localhost:8080/users/' + id,this.jwt()).map((response: Response) => response.json());
  }

  create(user: User) {
    return this.http.post('http://localhost:8080/users/', user,this.jwt()).map((response: Response) => response.json());
  }

  update(id: number,user: User) {
    return this.http.put('http://localhost:8080/users/' + id, user,this.jwt()).map((response: Response) => response.json());
  }

  delete(id: number) {
    return this.http.delete('http://localhost:8080/users/' + id,this.jwt()).map((response: Response) => response.json());
  }

  // private helper methods

  private jwt() {
    // create authorization header with jwt token
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser && currentUser.token) {
      let headers = new Headers({ 'Authorization': 'Bearer ' + currentUser.token });
      return new RequestOptions({ headers: headers });
    }
  }
}