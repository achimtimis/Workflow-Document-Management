import { Document } from '../documents/document';
import { User } from '../users/index';
export class Group {

  id: number;
  name: string;
  public users: Array<User>;

  constructor(id: number, name: string, users: Array<User>) {
    this.id = id;
    this.name = name;
    this.users = users;
  }
  
  
}