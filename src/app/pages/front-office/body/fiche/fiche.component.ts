import { Component, Input, OnInit } from '@angular/core';
import { Subscriber, Subscription } from 'rxjs';
import { HttpService } from 'src/app/http.service';
@Component({
  selector: 'app-fiche',
  templateUrl: './fiche.component.html',
  styleUrls: ['./fiche.component.css']
  // ,'../../../assets/css/style.css'
})
export class FicheComponent implements OnInit {

  public sign:any;
 
  // private roomSub: Subscription = new Subscription;


  constructor(private httpservice: HttpService) { 
    
  }


  public i=0;



  public imageList: any;

  public id:any;

  @Input('data')
  set data(data:any){
    this.id=data;
    if(data!=undefined){
    this.getSignDetails(data);
    }

  }




  // @Input() public data:any;



  ngOnInit(): void {
  //  if(this.id!=null){
  //    console.log("tsy null");
  //     this.getRoomDetails(this.id);
  //  }
  //  else{
  //    console.log(null);
  //  }
  }

 

  getSignDetails(id:any){
    // this.httpservice.getImages(id).subscribe((imgs:any) =>{
    //   this.imageList=imgs;
    //   console.log(this.imageList);
    // });

    this.httpservice.getThisSign(id).subscribe((data:any) =>{
      this.sign=data;
      this.imageList=data[0].images;
    
      console.log(this.sign);
      console.log(this.imageList);


    });
   

    // this.httpservice.getDetails(id).subscribe((Dets:any) =>{
    //   this.detailsList=Dets;
    //   console.log(this.detailsList);
    // });

    
  }



    // this.roomSub= this.httpservice.roomSubj.subscribe(
    //   (rooms: Room[]) => {
    //     this.room=rooms;
    //   } 
    // );
    // this.httpservice.getRoom(id);
    
    // console.log(this.httpservice.room);
  // }
  // private convert(object:any){
  //   const resultArray=Object.keys(object).map(index => {
  //     let room=object[index];
  //     return room;
  //   });
  // }
  

}
