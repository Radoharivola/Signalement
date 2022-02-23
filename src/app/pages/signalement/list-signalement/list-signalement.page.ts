import { Component, OnInit } from '@angular/core';
import {ListServiceService} from './list-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-signalement',
  templateUrl: './list-signalement.page.html',
  styleUrls: ['./list-signalement.page.scss'],
})
export class ListSignalementPage implements OnInit {
  allSign;
  constructor(private listService: ListServiceService,private router: Router) { }

  ngOnInit() {
    this.loadAllSignalements();
  }
  loadAllSignalements(){
    this.listService.getAllSignalement().subscribe((data: any) => {
      if (typeof data.data !== undefined && data.data == false) {
        alert('azertyui');
      } else {
        this.allSign = data;
        console.log(this.allSign);
      }
    });
  }
  goToPage(page:string):void {
    this.router.navigate([`${page}`])
  }
}
