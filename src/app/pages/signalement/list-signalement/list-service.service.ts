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
    return this.http.get('https://s5-signalement.herokuapp.com/Signalements/User',{'headers':this.headers});
  }
}
