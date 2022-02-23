import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppUser } from './AppUser';

@Injectable({
  providedIn: 'root'
})
export class RegisterServiceService {
  headers ={'content-type': 'application/json'};

  constructor(private http: HttpClient) { }
  register(appUser:AppUser){
    const body = JSON.stringify(appUser);
    return this.http.post('http://localhost:8080/inscription',body,{'headers':this.headers});
  }
}
