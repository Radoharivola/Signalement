import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListSignalementComponent } from './pages/backoffice/body/list-signalement/list-signalement.component';
import { AdminComponent } from './pages/backoffice/body/admin/admin.component';

import { AuthComponent } from './pages/backoffice/auth/auth.component';
// import { MapComponent } from './pages/backoffice/body/map/map.component';
// import { StatsComponent } from './pages/backoffice/body/stats/stats.component';
import { RegionComponent } from './pages/backoffice/body/region/region.component';
import { TypeProblemComponent } from './pages/backoffice/body/type-problem/type-problem.component';
import { BackOfficeComponent } from './pages/backoffice/back-office.component';
import { FrontOfficeComponent } from './pages/front-office/front-office.component';
import { MysignalementComponent } from './pages/front-office/body/mysignalement/mysignalement.component';
import { SignalementEnCoursComponent } from './pages/front-office/body/signalement-en-cours/signalement-en-cours.component';
import { SignalementTermineComponent } from './pages/front-office/body/signalement-termine/signalement-termine.component';



const routes: Routes = [
  { path: '', redirectTo:'/FrontOffice/(frontOffice:mySignalements)',pathMatch:"full"},
  { path:'BackOffice',component:BackOfficeComponent,
      children:[
        { path: 'list', component: ListSignalementComponent , outlet: "backOffice"},
        { path: 'admin', component: AdminComponent ,  outlet: "backOffice"},
        { path: 'login', component: AuthComponent ,  outlet: "backOffice"},
        // { path:'map',component:MapComponent,  outlet: "backOffice"},
        // {path:'stats',component:StatsComponent,  outlet: "backOffice"},
        {path: 'region', component:RegionComponent,  outlet: "backOffice"},
        {path:'typeproblem',component:TypeProblemComponent,  outlet: "backOffice"},
      ]},
  { path:'FrontOffice',component:FrontOfficeComponent , children:[
      {path:'mySignalements',component:MysignalementComponent,outlet:"frontOffice"},
      {path:'signalementEnCours',component:SignalementEnCoursComponent,outlet:"frontOffice"},
      {path:'signalementTermine',component:SignalementTermineComponent,outlet:"frontOffice"},
    ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
