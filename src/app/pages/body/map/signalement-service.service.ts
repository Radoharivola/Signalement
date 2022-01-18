import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SignalementServiceService {

  constructor(private http: HttpClient) { }
  getNASignalement(){
    return this.http.get('http://localhost:8080/NASignalements');
  }
  getSignalementById(id: number){
    return this.http.get('http://localhost:8080/Signalements/'+id);
  }
}
