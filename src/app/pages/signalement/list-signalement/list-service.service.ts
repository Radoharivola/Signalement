import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
// import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ListServiceService {
  headers ={'content-type': 'application/json','token': localStorage.getItem('token')};
  constructor(private http:HttpClient) { }
  getAllSignalement(){
    return this.http.get('http://localhost:8080/Signalements/User',{'headers':this.headers});
  }
}
