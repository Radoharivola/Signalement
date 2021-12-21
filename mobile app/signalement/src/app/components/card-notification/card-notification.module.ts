import { NgModule } from '@angular/core';
import { IonicModule } from '@ionic/angular';

import { CardNotificationComponent } from '../card-notification/card-notification.component';

const components = [
  CardNotificationComponent
];

@NgModule({
  imports: [
    IonicModule
  ],
  declarations: components,
  exports: components
})
export class CardNotificationComponentsModule { }
