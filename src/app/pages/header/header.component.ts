import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  ngOnInit(): void {
  }
  constructor(private router: Router){};
  goToPage(pageName:string):void {
    this.router.navigate([`${pageName}`]);
  }

}
