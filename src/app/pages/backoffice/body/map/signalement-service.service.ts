import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Affectation } from './Affectation';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
    providedIn: 'root'
})
export class SignalementServiceService {
    // +

    headers = { 'content-type': 'application/json','token':this.cookieService.get('token')};
    constructor(private http: HttpClient, private cookieService: CookieService) { }
    getNASignalement() {
        return this.http.get('https://s5-signalement.herokuapp.com/NASignalements/',{ 'headers': this.headers });
    }
    getSignalementById(id: number) {
        return this.http.get('https://s5-signalement.herokuapp.com/Signalements/' + id,{ 'headers': this.headers });
    }
    getRegions() {
        return this.http.get('https://s5-signalement.herokuapp.com/Regions',{ 'headers': this.headers });
    }
    affectRegion(aff: Affectation): Observable<any> {

        const body = JSON.stringify(aff);
        return this.http.post('https://s5-signalement.herokuapp.com/affectations/', body, { 'headers': this.headers })
    }
}
