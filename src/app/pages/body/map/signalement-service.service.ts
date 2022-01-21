import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Affectation } from './Affectation';
import { Observable } from 'rxjs';

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
  getRegions(){
    return this.http.get('http://localhost:8080/Regions');
  }
  affectRegion(aff: Affectation): Observable<any> {
    const headers = { 'content-type': 'application/json' }
    const body = JSON.stringify(aff);
    console.log(body)
    return this.http.post('http://localhost:8080/affectations', body, { 'headers': headers })
  }
}
