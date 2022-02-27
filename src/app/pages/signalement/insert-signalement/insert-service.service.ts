import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
// import { Observable } from 'rxjs';
import { NewSignalement } from './NewSignalement';

@Injectable({
  providedIn: 'root'
})
export class InsertServiceService {
  headers ={'content-type': 'application/json','token': localStorage.getItem('token')};
  constructor(private http: HttpClient) { }
  getAllTypes(){
    return this.http.get('https://s5-signalement.herokuapp.com/TypeSignalements',{'headers':this.headers});
  }
  insertSignalement(newSignalement:NewSignalement){
    const body=JSON.stringify(newSignalement);
    return this.http.post('https://s5-signalement.herokuapp.com/Signalements',body,{'headers':this.headers});
  }
}
