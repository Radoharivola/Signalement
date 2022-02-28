import { Component, OnInit } from '@angular/core';
import { HttpService } from 'src/app/http.service';
@Component({
  selector: 'app-mysignalement',
  templateUrl: './mysignalement.component.html',
  styleUrls: ['./mysignalement.component.css']
})
export class MysignalementComponent implements OnInit {

  constructor(private http: HttpService) { }

  public idSignTemp:any;
  public signalements:any;
  public msg:any;
  public idSign:any;

  public lim1=0;
  public lim2=4;

  public realList:any;
  ngOnInit(): void {
    this.getSignalement();
  }


  public getSignalement(){
    this.http.getSignalement("").subscribe((data:any)=>{
      console.log(data);
      this.signalements=data;
      this.setPagination(this.lim1, this.lim2);

    })
  }

  public choosen(id:any){
    this.idSignTemp=id;
  }

  public execute(){
    this.http.insertToEnCours(this.idSignTemp).subscribe((data:any)=>{
      console.log(data);
        if(data=="Error"){
            this.msg=0;
        }
        else if(data=="Success"){
          this.msg=1;
        }

        (document.getElementById("hiden") as HTMLInputElement).click();
    });
    console.log()
  }

  setPagination(nb1: number, nb2: number) {

    this.realList = [];
    for (let i = nb1; i <= nb2; i++) {
      if (i < this.signalements.length) {
        this.realList.push(this.signalements[i]);

      }
    }
  }

  next() {
    if (this.lim2 + 1 < this.signalements.length) {
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

  public setId(id:any){
    this.idSign=id;
  }


}
