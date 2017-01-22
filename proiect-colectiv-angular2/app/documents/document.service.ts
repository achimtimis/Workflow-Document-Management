import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { DocumentFlux } from '.././document.flux/index';
import { Document, UserDocument } from './index';
import { User } from '.././users/index';
import { Group } from '.././groups';

@Injectable()
export class DocumentService {
  constructor(private http: Http) { }

  //TODO: look into using observables i.e. getAll(): Observable<Response>

  getAll() {
    return this.http.get('http://localhost:8080/documents/all', this.jwt()).map((response: Response) => response.json());
  }

  getWorkZone() {
    return this.http.get('http://localhost:8080/documents/workzone', this.jwt()).map((response: Response) => response.json());
  }

  getTaskZone() {
    return this.http.get('http://localhost:8080/documents/taskwz', this.jwt()).map((response: Response) => response.json());
  }

  getCompletedZone() {
    return this.http.get('http://localhost:8080/documents/completedwz', this.jwt()).map((response: Response) => response.json());
  }

  getActiveZone() {
    return this.http.get('http://localhost:8080/documents/activewz', this.jwt()).map((response: Response) => response.json());
  }
  getAllFluxes() {
    return this.http.get('http://localhost:8080/documents/flux', this.jwt()).map((response: Response) => response.json());
  }
  getAllDocumentFluxForId(id: number) {
    return this.http.get('http://localhost:8080/documents/flux/' + id, this.jwt()).map((response: Response) => response.json());
  }
  getById(id: number) {
    return this.http.get('http://localhost:8080/documents/byUser/' + id, this.jwt()).map((response: Response) => response.json());
  }

  getDocumentById(id: number) {
    return this.http.get('http://localhost:8080/documents/' + id, this.jwt()).map((response: Response) => response.json());
  }
  create(document: Document, user: User) {
    let userDoc = new UserDocument(document, user);
    return this.http.post('http://localhost:8080/documents/create/', userDoc, this.jwt()).map((response: Response) => response.json());
  }

  createDocumentOnly(document: Document) {
    return this.http.post('http://localhost:8080/documents/', document, this.jwt()).map((response: Response) => response.json());
  }
  update(id: number, user: User, document: Document) {
    let userDoc = new UserDocument(document, user);
    return this.http.put('http://localhost:8080/documents/' + id, userDoc, this.jwt()).map((response: Response) => response.json());
  }

  delete(id: number) {
    return this.http.delete('http://localhost:8080/documents/' + id, this.jwt()).map((response: Response) => response.json());
  }

  addFlux(documents: Array<Document>, userGroups: Array<Group>) {
    let flux = new DocumentFlux(documents, userGroups);
    return this.http.post('http://localhost:8080/documents/createFlux/', flux, this.jwt()).map((response: Response) => response.json());
  }

  acceptDocument(user: User, document: Document) {
    let userDoc = new UserDocument(document, user);
    return this.http.post('http://localhost:8080/documents/flux/accept/', userDoc, this.jwt()).map((response: Response) => response.json());
  }

  denyDocument(user: User, document: Document) {
    let userDoc = new UserDocument(document, user);
    return this.http.post('http://localhost:8080/documents/flux/deny/', userDoc, this.jwt()).map((response: Response) => response.json());
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