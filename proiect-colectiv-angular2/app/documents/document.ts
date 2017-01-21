export class Document {

  constructor(id: number, version: string, author: string, creationDate: string,
    abstractText: string, keywords: string, lastEditedOn: string, lastEditedBy: string,
    name: string, details: string, documentType: string, status: string) { }

  get id(): number {
    return this.id;
  }

  get version(): string {
    return this.version;
  }

  get author(): string {
    return this.author;
  }

  get creationDate(): string {
    return this.creationDate;
  }

  get abstractText(): string {
    return this.abstractText;
  }

  get keywords(): string {
    return this.keywords;
  }

  get lastEditedOn(): string {
    return this.lastEditedOn;
  }

  get lastEditedBy(): string {
    return this.lastEditedBy;
  }

  get name(): string {
    return this.name;
  }

  get details(): string {
    return this.details;
  }

  get documentType(): string {
    return this.documentType;
  }

  get status(): string {
    return this.status;
  }
}