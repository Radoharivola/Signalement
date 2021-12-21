import {NgModule} from '@angular/core';
import {IonicModule} from '@ionic/angular';

import {CardSignalementComponent} from '../card-signalement/card-signalement.component';

const components = [
    CardSignalementComponent
];

@NgModule({
  imports: [
    IonicModule
  ],
  declarations: components,
  exports: components
})
export class CardSignalementComponentsModule { }
