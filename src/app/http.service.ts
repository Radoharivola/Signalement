import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject, Observable } from 'rxjs';
import { Admin } from './pages/backoffice/body/admin/Admin';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  constructor(private httpclient: HttpClient,private cookieService: CookieService) { }
  headers = { 'content-type': 'application/json', 'token': this.cookieService.get('token') };


  apiRegion = "https://s5-signalement.herokuapp.com/Regions";

  apiAdmin = "https://s5-signalement.herokuapp.com/Admins"

  apiTypeSignalement = "https://s5-signalement.herokuapp.com/TypeSignalements";


  apiNASignalement = "https://s5-signalement.herokuapp.com/NASignalements";

  apiSignalement = "https://s5-signalement.herokuapp.com/Signalements";

  apiEnCours = "https://s5-signalement.herokuapp.com/EnCours";

  apiTermine = "https://s5-signalement.herokuapp.com/Termine";




  public getRegion() {
    return this.httpclient.get(this.apiRegion, { 'headers': this.headers });
  }
  public ajoutRegion(nom: String) {
    return this.httpclient.post(this.apiRegion, { _Nom: nom }, { 'headers': this.headers });
  }

  public modifierRegion(id: any, nom: String) {
    return this.httpclient.put(this.apiRegion, { _Id: id, _Nom: nom }, { 'headers': this.headers });
  }
  public getSingleRegion(id: String) {
    return this.httpclient.get(this.apiRegion + "/" + id, { 'headers': this.headers });

  }

  public deleteRegion(id: String) {
    return this.httpclient.delete(this.apiRegion + "/" + id, { 'headers': this.headers });
  }

  public getAdmins() {
    return this.httpclient.get(this.apiAdmin, { 'headers': this.headers });
  }
  public getAdmin(id: any) {
    return this.httpclient.get(this.apiAdmin + "/" + id, { 'headers': this.headers });
  }

  public addAdmin(admin: Admin) {
    const body = JSON.stringify(admin);
    return this.httpclient.post(this.apiAdmin, body, { 'headers': this.headers });
  }

  public modifierAdmin(admin: Admin) {
    const body = JSON.stringify(admin);
    return this.httpclient.put(this.apiAdmin, body, { 'headers': this.headers });
  }

  public deleteAdmin(id: any) {
    return this.httpclient.delete(this.apiAdmin + "/" + id, { 'headers': this.headers });
  }

  public addTypeSign(nom: any) {
    return this.httpclient.post(this.apiTypeSignalement, { _Nom: nom }, { 'headers': this.headers });
  }

  public getTypeSign() {
    return this.httpclient.get(this.apiTypeSignalement, { 'headers': this.headers });
  }

  public modifTypeSign(id: any, nom: any) {
    return this.httpclient.put(this.apiTypeSignalement, { _Id: id, _Nom: nom }, { 'headers': this.headers });
  }

  public deleteTypeSign(id: any) {
    return this.httpclient.delete(this.apiTypeSignalement + "/" + id + "/", { 'headers': this.headers });
  }


  ///////////////////////////////     FRONT-OFFICE

  public getSignalement(token: any) {
    // return this.httpclient.get(this.apiSignalement+"/"+token);
    return this.httpclient.get(this.apiSignalement+"/Regions", { 'headers': this.headers });

  }

  public getThisSign(id: any) {
    return this.httpclient.get(this.apiSignalement + '/' + id, { 'headers': this.headers });
  }


  public insertToEnCours(idSign: any) {
    return this.httpclient.post(this.apiEnCours, { _IdSignalement: idSign }, { 'headers': this.headers });
  }

  public getEnCours() {
    return this.httpclient.get(this.apiEnCours, { 'headers': this.headers });
  }


  public insertToTermine(idSign: any) {
    return this.httpclient.post(this.apiTermine, { _IdSignalement: idSign }, { 'headers': this.headers });
  }

  public getTermine() {
    return this.httpclient.get(this.apiTermine, { 'headers': this.headers });

  }



}
