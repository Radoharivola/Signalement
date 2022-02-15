import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';
import { Subject, Observable } from 'rxjs';
import { Admin } from './pages/body/admin/Admin';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private httpclient: HttpClient,private cookieService: CookieService) { }


  apiRegion="http://localhost:8080/Regions";

  apiAdmin="http://localhost:8080/Admins"

  apiTypeSignalement="http://localhost:8080/TypeSignalements";

  public getRegion(){
    return this.httpclient.get(this.apiRegion+"/"+this.cookieService.get('token'));
  }
  public ajoutRegion(nom:String){
    return this.httpclient.post(this.apiRegion+"/"+this.cookieService.get('token'),{ _Nom:nom} );
  }

  public modifierRegion(id:any,nom:String){
    return this.httpclient.put(this.apiRegion+"/"+this.cookieService.get('token'),{ _Id:id, _Nom:nom});
  }
  public getSingleRegion(id:String){
    return this.httpclient.get(this.apiRegion+"/"+id+"/"+this.cookieService.get('token'));

  }

  public deleteRegion(id:String){
    return this.httpclient.delete(this.apiRegion+"/"+id+"/"+this.cookieService.get('token'));
  }

  public getAdmins(){
    return this.httpclient.get(this.apiAdmin+"/"+this.cookieService.get('token'));
  }
  public getAdmin(id:any){
    return this.httpclient.get(this.apiAdmin+"/"+id+"/"+this.cookieService.get('token'));
  }

  public addAdmin(admin:Admin){
    const headers= { 'content-type': 'application/json'}
    const body=JSON.stringify(admin);
    return this.httpclient.post(this.apiAdmin+"/"+this.cookieService.get('token'),body, { 'headers':headers});
  }

  public modifierAdmin(admin:Admin){
    const headers= { 'content-type': 'application/json'}
    const body=JSON.stringify(admin);
    return this.httpclient.put(this.apiAdmin+"/"+this.cookieService.get('token'),body, { 'headers':headers});
  }

  public deleteAdmin(id:any){
    return this.httpclient.delete(this.apiAdmin+"/"+id+"/"+this.cookieService.get('token'));
  }

  public addTypeSign(nom:any){
    return this.httpclient.post(this.apiTypeSignalement+"/"+this.cookieService.get('token'),{ _Nom:nom});
  }

  public getTypeSign(){
    return this.httpclient.get(this.apiTypeSignalement+"/"+this.cookieService.get('token'));
  }

  public modifTypeSign(id:any,nom:any){
    return this.httpclient.put(this.apiTypeSignalement+"/"+this.cookieService.get('token'),{_Id:id,_Nom:nom});
  }

  public deleteTypeSign(id:any){
    return this.httpclient.delete(this.apiTypeSignalement+"/"+id+"/"+this.cookieService.get('token'));
  }
}
