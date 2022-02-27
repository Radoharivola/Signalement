import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-details-notification',
  templateUrl: './details-notification.page.html',
  styleUrls: ['./details-notification.page.scss'],
})
export class DetailsNotificationPage implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }
  goToPage(page: string): void {
    this.router.navigate([`${page}`])
  } 
}
