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
    return this.http.get('http://localhost:8080/TypeSignalements',{'headers':this.headers});
  }
  insertSignalement(newSignalement:NewSignalement){
    const body=JSON.stringify(newSignalement);
    return this.http.post('http://localhost:8080/Signalements',body,{'headers':this.headers});
  }
}
