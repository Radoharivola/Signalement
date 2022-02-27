import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class NotificationServiceService {
  headers ={'content-type': 'application/json','token': localStorage.getItem('token')};

  constructor(private http: HttpClient) { }
  getAllNotifications(){
    return this.http.get('https://s5-signalement.herokuapp.com/notifications',{'headers':this.headers});
  }
}
