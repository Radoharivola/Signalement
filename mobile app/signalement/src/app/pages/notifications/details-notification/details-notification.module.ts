import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { DetailsNotificationPageRoutingModule } from './details-notification-routing.module';

import { DetailsNotificationPage } from './details-notification.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    DetailsNotificationPageRoutingModule
  ],
  declarations: [DetailsNotificationPage]
})
export class DetailsNotificationPageModule {}
