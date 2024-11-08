export interface ILoginDTO{
    username?:string;
    password?:string;
    idRol?:number;
}

export class LoginDTO implements ILoginDTO{
    username?:string;
    password?:string;
    idRol?:number;
}