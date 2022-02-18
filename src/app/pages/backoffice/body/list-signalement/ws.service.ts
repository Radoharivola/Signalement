import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class WsService {

  constructor(private http: HttpClient) { }
  getSignalement(){
    return this.http.get('http://localhost:8080/Signalements');
  }
}
