import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { InsertSignalementPage } from './insert-signalement.page';

const routes: Routes = [
  {
    path: '',
    component: InsertSignalementPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class InsertSignalementPageRoutingModule {}
