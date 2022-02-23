import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppUser } from './AppUser';
import { RegisterServiceService } from './register-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {
  appUser=new AppUser();
  errorMessage;
  constructor(private registerServire: RegisterServiceService,private router: Router) { }

  ngOnInit() {

  }
  register() {
    this.registerServire.register(this.appUser).subscribe((data: any) => {
      if (typeof data.data !== undefined && data.connectionStatus == false) {
        console.log(data);
        this.errorMessage= data.message;
      }
      else{
        this.goToPage('login');
      }
    });
  }
  goToPage(page:string):void {
    this.router.navigate([`${page}`])
  }
}
