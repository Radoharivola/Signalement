import { Component, OnInit } from '@angular/core';
import { WsService } from './ws.service';
@Component({
  selector: 'app-list-signalement',
  templateUrl: './list-signalement.component.html',
  styleUrls: ['./list-signalement.component.css']
})
export class ListSignalementComponent implements OnInit {
  headElements = ['Type de Signalement', 'Region', 'Date'];
  constructor(private WsService: WsService) { }
  ngOnInit(): void {
    this.getSignalement();
  }
  public signalements: any;
  private getSignalement() {
    this.WsService.getSignalement().subscribe((data: any) => {
      this.signalements = data;
      console.log(data);
      console.log(data[0].type[0].nom);
    });
  }

}
