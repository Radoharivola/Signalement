import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ListSignalementPageRoutingModule } from './list-signalement-routing.module';

import { ListSignalementPage } from './list-signalement.page';
import {CardSignalementComponentsModule} from '../../../components/card-signalement/card-signalement.module'

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ListSignalementPageRoutingModule,
    CardSignalementComponentsModule
  ],
  declarations: [ListSignalementPage]
})
export class ListSignalementPageModule {}
