import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppUser } from './AppUser';
// import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {
  headers = { 'content-type': 'application/json' };

  constructor(private http: HttpClient) { }

  login(appUser: AppUser) {
    const body = JSON.stringify(appUser);
    return this.http.post('https://s5-signalement.herokuapp.com/Login', body, { 'headers': this.headers });
  }
}
