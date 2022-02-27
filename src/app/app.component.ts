import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  logedIn: boolean = true;
  title = 'Signalement';
  constructor(private router: Router, private cookieService: CookieService,private activatedRoute: ActivatedRoute) { };

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
    // this.router.navigate([`${pageName}`]);
    this.router.navigate([`${pageName}`], {relativeTo: this.activatedRoute});
  }
}
