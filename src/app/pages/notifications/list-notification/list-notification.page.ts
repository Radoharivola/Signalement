import { Component, OnInit } from '@angular/core';
import {NotificationServiceService} from './notification-service.service';
@Component({
  selector: 'app-list-notification',
  templateUrl: './list-notification.page.html',
  styleUrls: ['./list-notification.page.scss'],
})
export class ListNotificationPage implements OnInit {
  allNotifications;
  constructor(private notificationService: NotificationServiceService) { }

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

}
