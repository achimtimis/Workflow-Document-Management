﻿import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { User } from './user';


@Injectable()
export class UserService {
  constructor(private http: Http) { }

  //TODO: look into using observables i.e. getAll(): Observable<Response>
  getAll() {
    return this.http.get('http://localhost:8080/users', this.jwt()).map((response: Response) => response.json());
  }

  getById(id: number) {
    return this.http.get('http://localhost:8080/users/' + id, this.jwt()).map((response: Response) => response.json());
  }

  create(user: User) {
    return this.http.post('http://localhost:8080/users/', user, this.jwt()).map((response: Response) => response.json());
  }

  update(id: number, user: User) {
    return this.http.put('http://localhost:8080/users/' + id, user, this.jwt()).map((response: Response) => response.json());
  }

  delete(id: number) {
    return this.http.delete('http://localhost:8080/users/' + id, this.jwt()).map((response: Response) => response.json());
  }

  addGroup(name: string, user: User) {
    return this.http.post('http://localhost:8080/users/addToGroup/' + name, user, this.jwt()).map((response: Response) => response.json());
  }

  getAllGroups(){
    return this.http.get('http://localhost:8080/users/groups',this.jwt()).map((response: Response) => response.json())
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