import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { Document } from './document';



@Injectable()
export class UserService {
  constructor(private http: Http) { }

  

  //TODO: look into using observables i.e. getAll(): Observable<Response>
  
  getAll() {
    return this.http.get('http://localhost:8080/documents/all',this.jwt()).map((response: Response) => response.json());
  }

  getById(id: number) {
    return this.http.get('http://localhost:8080/document/' + id,this.jwt()).map((response: Response) => response.json());
  }

  create(document: Document) {
   return this.http.post('http://localhost:8080/users/', document,this.jwt()).map((response: Response) => response.json());
  }

  update(id: number,document: Document) {
    return this.http.put('http://localhost:8080/users/' + id, document,this.jwt()).map((response: Response) => response.json());
  }

  delete(id: number) {
    return this.http.delete('http://localhost:8080/users/' + id,this.jwt()).map((response: Response) => response.json());
  }


  private jwt() {
    // create authorization header with jwt token
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser && currentUser.token) {
      let headers = new Headers({ 'Authorization': 'Bearer ' + currentUser.token });
      return new RequestOptions({ headers: headers });
    }
  }
}