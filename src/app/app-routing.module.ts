import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListSignalementComponent} from '../app/pages/signalement/list-signalement/list-signalement.component';

const routes: Routes = [
  {path: 'signalement', component: ListSignalementComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
