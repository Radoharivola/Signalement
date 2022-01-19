import { Component, OnInit } from '@angular/core';
import { HttpService } from 'src/app/http.service';

@Component({
  selector: 'app-type-problem',
  templateUrl: './type-problem.component.html',
  styleUrls: ['./type-problem.component.css']
})
export class TypeProblemComponent implements OnInit {

  constructor(private http: HttpService ) { }

  ngOnInit(): void {
    this.getTypeSigns();
  }
  public TypeSignList:any;
  public idTemp:any;
  public nomTemp:any;
  public realList:any[]=[];
  public lim1=0;
  public lim2=4;

  

  getTypeSigns(){
    console.log("the data should be here");

      this.http.getTypeSign().subscribe((data:any)=>{
        this.TypeSignList=data;
        this.setPagination(this.lim1,this.lim2);
      });
  }

  // getSingleTypeSign(){
  //   this.http.getSingleTypeSign(this.idTemp).subscribe((data:any)=>{
  //     // this.nomTemp=data;
  //     // this.setPagination(this.lim1,this.lim2);
  //     console.log(data);
  //   });
  // }

  getTotalPage(){
    return (this.TypeSignList.length + 5-1)/ 5;
  }



  clickModif(id:any,nom:any){
    this.idTemp=id;
    this.nomTemp=nom;

    // this.getSingleTypeSign();

  }

  clickDel(id:any,nom:any){
    this.idTemp=id;
    // this.getSingleTypeSign();
  }

  ajout(){
    var nom=(document.getElementById("nom") as HTMLInputElement).value;
    console.log(nom);
    this.ajouterTypeSign(nom);
    this.getTypeSigns();

  }
  modifier(){
    this.modifTypeSign(this.idTemp,(document.getElementById("nomType") as HTMLInputElement).value);
  }

  ajouterTypeSign(nom:any){
    this.http.addTypeSign(nom).subscribe((data:any)=>{
      console.log("success");
    });
    this.getTypeSigns();
  }

  modifTypeSign(id:any,nom:any){ 
    this.http.modifTypeSign(id,nom).subscribe(data=>{
      console.log(data);
      this.getTypeSigns();
    })
  }

  setPagination(nb1:number,nb2:number){

    this.realList=[];
     for(let i=nb1;i<=nb2;i++){
       if(i<this.TypeSignList.length){
        this.realList.push(this.TypeSignList[i]);

       }
     }
  }

  next(){
    if(this.lim2<=this.TypeSignList.length){
      this.lim1=this.lim1+4;
      this.lim2=this.lim2+4;
      this.setPagination(this.lim1,this.lim2);

    }
   
  }

  previous(){
   
    if( this.lim1-4>=0){
      this.lim1=this.lim1-4;
      this.lim2=this.lim2-4;
      this.setPagination(this.lim1,this.lim2);

    }
    
  }

  deleteTypeSign(){
    this.http.deleteTypeSign(this.idTemp).subscribe(data=>{
      console.log("success");
      this.getTypeSigns();
    })
  }


}
