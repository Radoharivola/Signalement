import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListSignalementPage } from './list-signalement.page';

const routes: Routes = [
  {
    path: '',
    component: ListSignalementPage,
  },
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ListSignalementPageRoutingModule {}
