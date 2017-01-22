export class Document {


  public id: number;
  public version: string;
  public author: string;
  public creationDate: string;
  public abstractText: string;
  public keywords: string;
  public lastEditedOn: string;
  public lastEditedBy: string;
  public name: string;
  public details: string;
  public documentType: string;
  public status: string;
  constructor(id: number, version: string, author: string, creationDate: string,
    abstractText: string, keywords: string, lastEditedOn: string, lastEditedBy: string,
    name: string, details: string, documentType: string, status: string) {
    this.id = id;
    this.version = version;
    this.author = author;
    this.creationDate = creationDate;
    this.abstractText = abstractText;
    this.keywords = keywords;
    this.lastEditedOn = lastEditedOn;
    this.lastEditedBy = lastEditedBy;
    this.name = name;
    this.details = details;
    this.documentType = documentType;
    this.status = status;
  }


}