import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';
import { Subject, Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private httpclient: HttpClient) { }

  apiUrl="https://api.publicapis.org/entries";

  public getSignalement(){
    return this.httpclient.get(this.apiUrl);
  }
}
