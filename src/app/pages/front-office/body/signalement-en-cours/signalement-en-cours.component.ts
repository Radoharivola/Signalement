import { Component, OnInit } from '@angular/core';
import { HttpService } from 'src/app/http.service';
@Component({
  selector: 'app-signalement-en-cours',
  templateUrl: './signalement-en-cours.component.html',
  styleUrls: ['./signalement-en-cours.component.css']
})
export class SignalementEnCoursComponent implements OnInit {

  constructor(private http: HttpService) { }

  public listEncours:any;

  public realList:any;
  public lim1=0;
  public lim2=4;

  public idTemp:any;
  ngOnInit(): void {
    this.getEnCours();
  }


  public getEnCours(){
    this.http.getEnCours().subscribe((data:any)=>{
      this.listEncours=data;
      this.setPagination(this.lim1, this.lim2);

    });
  }

  public insertToTerminer(){
    this.http.insertToTermine(this.idTemp).subscribe((data:any)=>{
      console.log(data);
      this.getEnCours();
    })
  }

  public setId(id:any){
    this.idTemp=id;
  }




  setPagination(nb1: number, nb2: number) {

    this.realList = [];
    for (let i = nb1; i <= nb2; i++) {
      if (i < this.listEncours.length) {
        this.realList.push(this.listEncours[i]);

      }
    }
  }

  next() {
    if (this.lim2 + 1 < this.listEncours.length) {
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
