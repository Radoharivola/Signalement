import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { SuperAdmin } from './SuperAdmimn';
import { AuthService } from './auth.service';
@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  superAdmin = new SuperAdmin();
  message: string | undefined;
  test = false;
  constructor(private cookieService: CookieService, private authService: AuthService) { }

  ngOnInit(): void {
    if (this.cookieService.get('token') !== '') {
      window.localStorage.setItem('page', 'map');
      window.location.reload();
    }
  }
  login() {
    console.log(this.test);
    if (this.test) {
      console.log("admin region")
      this.authService.loginRegion(this.superAdmin).subscribe(data => {
        console.log(data);
        this.message = data.message;
        if (data.connectionStatus) {
          this.cookieService.set('token', data.token); // To Set Cookie
          window.localStorage.setItem('front', 'blabla');
          window.location.reload();
        }
      })
    } else {
      console.log("super admin")
      this.authService.login(this.superAdmin).subscribe(data => {
        console.log(data);
        this.message = data.message;
        if (data.connectionStatus) {
          this.cookieService.set('token', data.token); // To Set Cookie
          window.localStorage.setItem('back', 'blabla');
          window.location.reload();
        }
      })
    }

  }


}
