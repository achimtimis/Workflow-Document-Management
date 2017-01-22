import { Document } from '../documents/index';
import { User } from '../users/index';
import { Group } from '../groups/group';
export class DocumentFlux {

  id: number;
  documents: Array<Document>;
  groups: Array<Group>;

  constructor(id: number, documents: Array<Document>, groups: Array<Group>) {

    this.documents = documents;
    this.groups = groups;
    this.id = id;
  }
}