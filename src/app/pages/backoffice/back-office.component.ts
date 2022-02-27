import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
@Component({
  selector: 'app-back-office',
  templateUrl: './back-office.component.html',
  styleUrls: ['./back-office.component.css']
})
export class BackOfficeComponent implements OnInit {
  logedIn: boolean = true;
  title = 'Signalement';
  constructor(private router: Router, private cookieService: CookieService) { };

  ngOnInit(): void {
    if (this.cookieService.get('token')==='') {
      this.logedIn = false;
      this.goToPage('/BackOffice');
    }
    if(window.localStorage.getItem('front')!=null){
      window.localStorage.removeItem('front');
      this.goToPage('');
    }
    if(window.localStorage.getItem('back')!=null){
      window.localStorage.removeItem('back');
      this.goToPage('map');
    }
  }

  goToPage(pageName: string): void {
    this.router.navigate([`${pageName}`]);
  }
}
