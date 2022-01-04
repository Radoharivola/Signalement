import { Component, OnInit } from '@angular/core';
import{HttpService} from '../../../http.service';

@Component({
  selector: 'app-list-signalement',
  templateUrl: './list-signalement.component.html',
  styleUrls: ['./list-signalement.component.css']
})
export class ListSignalementComponent implements OnInit {


  
  constructor(  private httpservice: HttpService ) { }

  ngOnInit(): void {
    this.getSignalement();
  }

  public lists:any;

  private getSignalement(){


    this.httpservice.getSignalement().subscribe((data:any) => {
      this.lists=data;
      console.log("okkk");
      console.log(data);
     });
  }

}
