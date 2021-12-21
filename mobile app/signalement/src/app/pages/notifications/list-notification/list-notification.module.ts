import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ListNotificationPageRoutingModule } from './list-notification-routing.module';

import { ListNotificationPage } from './list-notification.page';
import {CardNotificationComponentsModule} from '../../../components/card-notification/card-notification.module';
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ListNotificationPageRoutingModule,
    CardNotificationComponentsModule
  ],
  declarations: [ListNotificationPage]
})
export class ListNotificationPageModule {}
