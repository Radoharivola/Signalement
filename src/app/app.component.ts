import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {

  constructor(private router: Router) { }
  ngOnInit() {
    // alert(localStorage.getItem('token'));
    if (localStorage.getItem('token') == null) {
      this.goToPage('login');
    }
  }
  goToPage(page: string): void {
    this.router.navigate([`${page}`])
  }
}
