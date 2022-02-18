import { Component } from '@angular/core';
import { Router } from '@angular/router';
// import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  logedIn: boolean = true;
  title = 'Signalement';
  constructor(private router: Router) { };

  ngOnInit(): void {
    // if (this.cookieService.get('token')==='') {
    //   this.logedIn = false;
    //   this.goToPage('login');
    // }
    // if(window.localStorage.getItem('page')!=null){
    //   window.localStorage.removeItem('page');
    //   this.goToPage('map');
    // }
  }

  goToPage(pageName: string): void {
    this.router.navigate([`${pageName}`]);
  }
}
