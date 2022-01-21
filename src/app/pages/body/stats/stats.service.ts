import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StatsService {

  constructor(private http: HttpClient) { }
  getStats(){
    return this.http.get('http://localhost:8080/typeGlobalStat/2000');
  }
}
