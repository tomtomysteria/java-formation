export interface Role {
  name: string;
}

export interface User {
  uuid: string;
  username: string;
  roles: Role[];
}
