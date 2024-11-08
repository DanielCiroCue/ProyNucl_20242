import { Injectable } from '@angular/core';
import { Constants } from '../util/constants';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginDTO } from '../model/login-dto-model';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private url = Constants.API_BASE_URL;

  constructor(private http: HttpClient) { }

  login(loginDTO: LoginDTO): Observable<any> {
    return this.http.post(this.url + 'login', loginDTO);
  }
}
