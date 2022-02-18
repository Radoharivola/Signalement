import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';
import { Subject, Observable } from 'rxjs';
import { Admin } from './pages/backoffice/body/admin/Admin';
// import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private httpclient: HttpClient) { }


  apiRegion="http://localhost:8080/Regions";

  apiAdmin="http://localhost:8080/Admins"

  apiTypeSignalement="http://localhost:8080/TypeSignalements";

  public getRegion(){
    return this.httpclient.get(this.apiRegion+"/");
  }
  public ajoutRegion(nom:String){
    return this.httpclient.post(this.apiRegion+"/",{ _Nom:nom} );
  }

  public modifierRegion(id:any,nom:String){
    return this.httpclient.put(this.apiRegion+"/",{ _Id:id, _Nom:nom});
  }
  public getSingleRegion(id:String){
    return this.httpclient.get(this.apiRegion+"/"+id+"/");

  }

  public deleteRegion(id:String){
    return this.httpclient.delete(this.apiRegion+"/"+id+"/");
  }

  public getAdmins(){
    return this.httpclient.get(this.apiAdmin+"/");
  }
  public getAdmin(id:any){
    return this.httpclient.get(this.apiAdmin+"/"+id+"/");
  }

  public addAdmin(admin:Admin){
    const headers= { 'content-type': 'application/json'}
    const body=JSON.stringify(admin);
    return this.httpclient.post(this.apiAdmin+"/",body, { 'headers':headers});
  }

  public modifierAdmin(admin:Admin){
    const headers= { 'content-type': 'application/json'}
    const body=JSON.stringify(admin);
    return this.httpclient.put(this.apiAdmin+"/",body, { 'headers':headers});
  }

  public deleteAdmin(id:any){
    return this.httpclient.delete(this.apiAdmin+"/"+id+"/");
  }

  public addTypeSign(nom:any){
    return this.httpclient.post(this.apiTypeSignalement+"/",{ _Nom:nom});
  }

  public getTypeSign(){
    return this.httpclient.get(this.apiTypeSignalement+"/");
  }

  public modifTypeSign(id:any,nom:any){
    return this.httpclient.put(this.apiTypeSignalement+"/",{_Id:id,_Nom:nom});
  }

  public deleteTypeSign(id:any){
    return this.httpclient.delete(this.apiTypeSignalement+"/"+id+"/");
  }
}
