import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.page.html',
  styleUrls: ['./menu.page.scss'],
})
export class MenuPage implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
    if(localStorage.getItem('token') == null){
      this.goToPage('login');
    }
  }
  deco(){
    localStorage.removeItem('token');
    this.goToPage('login');
  }

  goToPage(page:string):void {
    this.router.navigate([`${page}`])
  }
}
