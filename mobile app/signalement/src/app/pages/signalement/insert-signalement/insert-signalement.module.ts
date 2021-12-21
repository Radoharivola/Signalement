import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { InsertSignalementPageRoutingModule } from './insert-signalement-routing.module';

import { InsertSignalementPage } from './insert-signalement.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    InsertSignalementPageRoutingModule
  ],
  declarations: [InsertSignalementPage]
})
export class InsertSignalementPageModule {}
