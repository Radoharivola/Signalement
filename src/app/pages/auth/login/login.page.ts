import { Component, OnInit } from '@angular/core';
import { NativeStorage } from '@ionic-native/native-storage/ngx';
import { AppUser } from './AppUser';
import { LoginServiceService } from './login-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  appUser = new AppUser();
  errorMessage = '';
  constructor(private storage: NativeStorage, private loginService: LoginServiceService, private router: Router) { }

  ngOnInit() {
  }
  login() {
    this.loginService.login(this.appUser).subscribe((data: any) => {
      if (typeof data.data !== undefined && data.connectionStatus == false) {
        this.errorMessage = data.message;
      } else {
        localStorage.setItem('token', data.token);
        this.goToPage('tabs');
      }
    });
  }
  goToPage(page: string): void {
    this.router.navigate([`${page}`])
  }
}
