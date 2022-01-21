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
    return this.http.get('https://s5-signalement.herokuapp.com/NASignalements');
  }
  getSignalementById(id: number){
    return this.http.get('https://s5-signalement.herokuapp.com/Signalements/'+id);
  }
  getRegions(){
    return this.http.get('https://s5-signalement.herokuapp.com/Region');
  }
  affectRegion(aff: Affectation): Observable<any> {
    const headers = { 'content-type': 'application/json' }
    const body = JSON.stringify(aff);
    console.log(body)
    return this.http.post('https://s5-signalement.herokuapp.com/affectations', body, { 'headers': headers })
  }
}
