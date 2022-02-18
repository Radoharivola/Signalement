// import { Component, OnInit } from '@angular/core';

// import {StatsService} from './stats.service';

// @Component({
//   selector: 'app-stats',
//   templateUrl: './stats.component.html',
//   styleUrls: ['./stats.component.css']
// })
// export class StatsComponent implements OnInit {
//   constructor(private statsService: StatsService) { }

  
//   dataArray: Number[] = [];
//   // ADD CHART OPTIONS. 
//   chartOptions = {
//     responsive: true    // THIS WILL MAKE THE CHART RESPONSIVE (VISIBLE IN ANY DEVICE).
//   }
//   labels :string[]= [];
//   // STATIC DATA FOR THE CHART IN JSON FORMAT.
//   chartData = [
//     {
//       label: 'Pourcentage',
//       data: this.dataArray
//     }
//   ];

//   // CHART COLOR.
//   colors = [
//     { // 1st Year.
//       backgroundColor: 'rgba(77,83,96,0.2)'
//     },
//     { // 2nd Year.
//       backgroundColor: 'rgba(30, 169, 224, 0.8)'
//     }
//   ]
//   ngOnInit(): void {
//     this.loadData();
//     this.loadLabels();
//   }
//   // CHART CLICK EVENT.
//   onChartClick(event: any) {
//     console.log(event);
//   }
//   loadLabels() {
//     let result:string[]=[];
//     this.statsService.getStats().subscribe((data: any) => {
//       for(let i = 0; i < data.length;i++){
//         result.push(data[i].nom);
//       }

//       this.labels=result;
//     });
//   }
//   loadData() {
//     let result:Number[]=[];
//     this.statsService.getStats().subscribe((data: any) => {
//       for(let i = 0; i < data.length;i++){
//         result.push(data[i].pourcentage);
//       }
//       this.chartData[0].data=result;
//     });
//   }
// }
