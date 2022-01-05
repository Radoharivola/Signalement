import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListSignalementComponent } from './pages/body/list-signalement/list-signalement.component';
import { HeaderComponent } from './pages/header/header.component';
import { BodyComponent } from './pages/body/body.component';
import { FooterComponent } from './pages/footer/footer.component';
import { RegionComponent } from './pages/body/region/region.component';
import { AdminComponent } from './pages/body/admin/admin.component';
import { TypeProblemComponent } from './pages/body/type-problem/type-problem.component';
import { StatsComponent } from './pages/body/stats/stats.component';
import {HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
@NgModule({
  declarations: [
    AppComponent,
    ListSignalementComponent,
    HeaderComponent,
    BodyComponent,
    FooterComponent,
    RegionComponent,
    AdminComponent,
    TypeProblemComponent,
    StatsComponent,
  ],
 
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
function routes(routes: any): any[] | import("@angular/core").Type<any> | import("@angular/core").ModuleWithProviders<{}> {
  throw new Error('Function not implemented.');
}

