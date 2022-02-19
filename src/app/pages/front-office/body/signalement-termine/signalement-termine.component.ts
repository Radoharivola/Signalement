import { Component, OnInit } from '@angular/core';
import { HttpService } from 'src/app/http.service';

@Component({
  selector: 'app-signalement-termine',
  templateUrl: './signalement-termine.component.html',
  styleUrls: ['./signalement-termine.component.css']
})
export class SignalementTermineComponent implements OnInit {

  constructor(private http: HttpService) { }

  public listTermine:any;

  public realList:any;
  public lim1=0;
  public lim2=4;
  ngOnInit(): void {
    this.getTermine();
  }


  public getTermine(){
    this.http.getTermine().subscribe((data:any)=>{
      this.listTermine=data;
      this.setPagination(this.lim1, this.lim2);

    });
  }




  setPagination(nb1: number, nb2: number) {

    this.realList = [];
    for (let i = nb1; i <= nb2; i++) {
      if (i < this.listTermine.length) {
        this.realList.push(this.listTermine[i]);

      }
    }
  }

  next() {
    if (this.lim2 + 1 < this.listTermine.length) {
      this.lim1 = this.lim1 + 4;
      this.lim2 = this.lim2 + 4;

      this.setPagination(this.lim1, this.lim2);

    }

  }

  previous() {

    if (this.lim1 - 4 >= 0) {
      this.lim1 = this.lim1 - 4;
      this.lim2 = this.lim2 - 4;
      this.setPagination(this.lim1, this.lim2);

    }

  }
}
