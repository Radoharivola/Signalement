import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
@Component({
  selector: 'app-front-office',
  templateUrl: './front-office.component.html',
  styleUrls: ['./front-office.component.css']
})
export class FrontOfficeComponent implements OnInit {
  logedIn: boolean = true;
  title = 'Signalement';
  constructor(private router: Router, private cookieService: CookieService) { };

  ngOnInit(): void {
    if (this.cookieService.get('token') === '') {
      this.logedIn = false;
      this.goToPage('/BackOffice');
    }
    if (window.localStorage.getItem('page') != null) {
      window.localStorage.removeItem('page');
      this.goToPage('map');
    }
  }

  goToPage(pageName: string): void {
    this.router.navigate([`${pageName}`]);
  }
}
