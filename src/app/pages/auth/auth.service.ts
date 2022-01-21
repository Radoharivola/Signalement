import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SuperAdmin } from './SuperAdmimn';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }
  
  login(superAdmin: SuperAdmin): Observable<any> {
    const headers = { 'content-type': 'application/json' }
    const body = JSON.stringify(superAdmin);
    console.log(body)
    return this.httpClient.post('http://localhost:8080/SULogin', body, { 'headers': headers })
  }
}
