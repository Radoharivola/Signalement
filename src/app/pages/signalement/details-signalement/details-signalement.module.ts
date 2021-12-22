import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { DetailsSignalementPageRoutingModule } from './details-signalement-routing.module';

import { DetailsSignalementPage } from './details-signalement.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    DetailsSignalementPageRoutingModule
  ],
  declarations: [DetailsSignalementPage]
})
export class DetailsSignalementPageModule {}
