import { Component, OnInit } from '@angular/core';
import { HttpService } from "../../../http.service";
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-region',
  templateUrl: './region.component.html',
  styleUrls: ['./region.component.css']
})
export class RegionComponent implements OnInit {

  constructor(private http: HttpService, private cookieService: CookieService) { }

  ngOnInit(): void {
    this.getRegions();
  }
  public regionList: any;
  public idTemp: any;
  public nomTemp: any;
  public realList: any[] = [];
  public lim1 = 0;
  public lim2 = 4;


  reLogin() {
    this.cookieService.delete('token');
    window.location.reload();
  }
  getRegions() {
    this.http.getRegion().subscribe((data: any) => {
      if(typeof data.data!=='undefined' && data.data==false){
        this.reLogin();
      }else{
        this.regionList = data;
        this.setPagination(this.lim1, this.lim2);
      }
    });
  }

  // getSingleRegion(){
  //   this.http.getSingleRegion(this.idTemp).subscribe((data:any)=>{
  //     // this.nomTemp=data;
  //     // this.setPagination(this.lim1,this.lim2);
  //     console.log(data);
  //   });
  // }

  getTotalPage() {
    return (this.regionList.length + 5 - 1) / 5;
  }



  clickModif(id: any, nom: any) {
    this.idTemp = id;
    this.nomTemp = nom;

    // this.getSingleRegion();

  }

  clickDel(id: any, nom: any) {
    this.idTemp = id;
    // this.getSingleRegion();
  }

  ajout() {
    var nom = (document.getElementById("nom") as HTMLInputElement).value;
    console.log(nom);
    this.ajouterRegion(nom);
    (document.getElementById("nom") as HTMLInputElement).value = "";
    this.getRegions();

  }
  modifier() {
    this.modifRegion(this.idTemp, (document.getElementById("nomRegion") as HTMLInputElement).value);
  }

  ajouterRegion(nom: any) {
    this.http.ajoutRegion(nom).subscribe((data: any) => {
      if (typeof data.data!=='undefined' && data.data==false) {
        this.reLogin();
      }else{
        this.getRegions();
      }
    });
  }

  modifRegion(id: any, nom: any) {
    this.http.modifierRegion(id, nom).subscribe(data => {
      console.log(data);
      this.getRegions();
    })
  }

  setPagination(nb1: number, nb2: number) {

    this.realList = [];
    for (let i = nb1; i <= nb2; i++) {
      if (i < this.regionList.length) {
        this.realList.push(this.regionList[i]);

      }
    }
  }

  next() {
    if (this.lim2 + 1 < this.regionList.length) {
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

  deleteRegion() {
    this.http.deleteRegion(this.idTemp).subscribe(data => {
      console.log("success");
      this.getRegions();
    })
  }




}
