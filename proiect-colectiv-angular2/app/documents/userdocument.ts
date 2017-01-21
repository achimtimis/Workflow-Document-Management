import { Document } from './document';
import { User } from '../users/index';
export class UserDocument {

  document: Document;
  user: User;
  constructor(document: Document, user: User) {
    this.document = document;
    this.user = user;
  }
}