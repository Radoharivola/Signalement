import { Component, OnInit } from '@angular/core';
import { HttpService } from 'src/app/http.service';
import { Admin } from './Admin';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor( private http: HttpService) { }

  ngOnInit(): void {
    this.getAdmins();
    
 

  }
  public listAdmins:any;
  public realList:any[]=[];
  public lim1=0;
  public lim2=2;
  public admin=new Admin();
  public adminTemp=new Admin();
  public regName="";
  public listRegion:any;
  public idTemp="";

  getAdmins(){
    this.http.getAdmins().subscribe((data:any)=>{
      console.log(data);
      this.listAdmins=data;
      this.setPagination(this.lim1,this.lim2);
     


    });
  }

  setPagination(nb1:number,nb2:number){

    this.realList=[];
     for(let i=nb1+1;i<=nb2;i++){
       if(i<this.listAdmins.length){
        this.realList.push(this.listAdmins[i]);

       }
     }
  }

  next(){
    if(this.lim2+1<this.listAdmins.length){
      this.lim1=this.lim1+2;
      this.lim2=this.lim2+2;
      this.setPagination(this.lim1,this.lim2);

    }
   
  }

  previous(){
   
    if( this.lim1-2>=0){
      this.lim1=this.lim1-2;
      this.lim2=this.lim2-2;
      this.setPagination(this.lim1,this.lim2);

    }
    
  }

  setListRegion(){
    this.http.getRegion().subscribe((data:any)=>{
      this.listRegion=data;
      console.log(this.listRegion);
    })
  }

  addAdmin(){
    // var nom=(document.getElementById("nom") as HTMLInputElement).value;
    // var email=(document.getElementById("email") as HTMLInputElement).value;
    // var mdp=(document.getElementById("mdp") as HTMLInputElement).value;
    var idRegion=(document.getElementById("idRegion") as HTMLSelectElement).value;
    this.admin._IdRegion=idRegion;
    this.http.addAdmin(this.admin).subscribe(data=>{
      console.log(data);
      this.admin._Email="";
      this.admin._Nom="";
      this.admin._Mdp="";
      this.getAdmins();


    });
  }

  clickDel(id:any){
    this.idTemp=id;
  }

  delete(){
    this.http.deleteAdmin(this.idTemp).subscribe(data=>{
      console.log("success");
    })
    this.getAdmins();
  }

  clickModif(id:any,nom:any,email:any,idRegion:any,region:any,mdp:any){
    console.log("id= "+id);
    console.log("idRegion= "+idRegion);

    this.adminTemp._Id=id;
    this.adminTemp._Nom=nom;
    this.adminTemp._Email=email;
    this.adminTemp._IdRegion=idRegion;
    this.adminTemp._Mdp=mdp;
    
    if(region.length!=0){
      this.regName=region[0].nom;

    }
    else{
      this.regName="Deleted";
    }
    this.setListRegion();

  }

  modif(){
    this.adminTemp._Id=(document.getElementById("idRegionModif") as HTMLInputElement).value;
    this.adminTemp._Nom=(document.getElementById("nom") as HTMLInputElement).value;
    this.adminTemp._Email=(document.getElementById("email") as HTMLInputElement).value;
    this.adminTemp._Mdp=(document.getElementById("mdp") as HTMLInputElement).value;
    console.log(this.adminTemp._IdRegion);

    this.adminTemp._IdRegion=(document.getElementById("idRegionModified") as HTMLInputElement).value;
    console.log(this.adminTemp._IdRegion);
    this.http.modifierAdmin(this.adminTemp).subscribe(data=>{
      console.log("success");
      this.getAdmins();
    });
    

  }




}
