import { Document } from '../documents/index';
import { User } from '../users/index';
import { Group } from '../groups/group';
export class DocumentFlux {

  documents: Array<Document>;
  groups: Array<Group>;

  constructor(documents: Array<Document>, groups: Array<Group>) {

    this.documents = documents;
    this.groups = groups;
  }
}