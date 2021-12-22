import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DetailsNotificationPage } from './details-notification.page';

const routes: Routes = [
  {
    path: '',
    component: DetailsNotificationPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DetailsNotificationPageRoutingModule {}
