import { Component, OnInit } from '@angular/core';
import {NotificationServiceService} from './notification-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-notification',
  templateUrl: './list-notification.page.html',
  styleUrls: ['./list-notification.page.scss'],
})
export class ListNotificationPage implements OnInit {
  allNotifications;
  constructor(private notificationService: NotificationServiceService,private router: Router) { }

  ngOnInit() {
    this.loadAllNotifications();
  }
  loadAllNotifications() {
    this.notificationService.getAllNotifications().subscribe((data: any) => {
      if (typeof data.data !== undefined && data.data == false) {
        alert('azertyui');
      } else {
        this.allNotifications = data;
        console.log(this.allNotifications);
      }
    });
  }
  
  goToPage(page: string): void {
    this.router.navigate([`${page}`])
  } 
  goToDetails(idSignalement:string): void {
    localStorage.setItem('idSignalement',idSignalement);
    this.goToPage('/tabs/detail');
  }
  reload(){
    location.reload();
  }
}
