import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Affectation } from './Affectation';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class SignalementServiceService {

  constructor(private http: HttpClient,private cookieService: CookieService) { }
  getNASignalement(){
    return this.http.get('http://localhost:8080/NASignalements/'+this.cookieService.get('token'));
  }
  getSignalementById(id: number){
    return this.http.get('http://localhost:8080/Signalements/'+id+'/'+this.cookieService.get('token'));
  }
  getRegions(){
    return this.http.get('http://localhost:8080/Regions/'+this.cookieService.get('token'));
  }
  affectRegion(aff: Affectation): Observable<any> {
    const headers = { 'content-type': 'application/json' }
    const body = JSON.stringify(aff);
    return this.http.post('http://localhost:8080/affectations/'+this.cookieService.get('token'), body, { 'headers': headers })
  }
}
