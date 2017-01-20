export class User {

  constructor(id: number, username: string, password: string, role: string) { }

  get username(): string {
    return this.username;
  }

  get role(): string {
    return this.role;
  }

  get id(): number {
    return this.id;
  }
  
  get password(): string {
    return this.password;
  }
}