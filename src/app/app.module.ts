import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListSignalementComponent } from './pages/backoffice/body/list-signalement/list-signalement.component';
import { HeaderComponent } from './pages/backoffice/header/header.component';
import { BodyComponent } from './pages/backoffice/body/body.component';
import { FooterComponent } from './pages/backoffice/footer/footer.component';
import { RegionComponent } from './pages/backoffice/body/region/region.component';
import { AdminComponent } from './pages/backoffice/body/admin/admin.component';
import { TypeProblemComponent } from './pages/backoffice/body/type-problem/type-problem.component';
// import { StatsComponent } from './pages/backoffice/body/stats/stats.component';
import {HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AuthComponent } from './pages/backoffice/auth/auth.component';
// import { CookieService } from 'ngx-cookie-service';
import { FormsModule } from '@angular/forms';
import { BackOfficeComponent } from './pages/backoffice/back-office.component';
import { FrontOfficeComponent } from './pages/front-office/front-office.component';
import { MysignalementComponent } from './pages/front-office/body/mysignalement/mysignalement.component';
import { SignalementEnCoursComponent } from './pages/front-office/body/signalement-en-cours/signalement-en-cours.component';
import { SignalementTermineComponent } from './pages/front-office/body/signalement-termine/signalement-termine.component';
import { HeaderFrontComponent } from './pages/front-office/header-front/header-front.component';
import { FooterFrontComponent } from './pages/front-office/footer-front/footer-front.component';
// import { MapComponent } from './pages/backoffice/body/map/map.component';

// resaka chart
// import {NgChartsModule} from 'ng2-charts';
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
    // StatsComponent,
    AuthComponent,
    ListSignalementComponent,
    BackOfficeComponent,
    FrontOfficeComponent,
    MysignalementComponent,
    SignalementEnCoursComponent,
    SignalementTermineComponent,
    HeaderFrontComponent,
    FooterFrontComponent,
    // MapComponent
  ],
 
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    // NgChartsModule
  ],
  // providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
function routes(routes: any): any[] | import("@angular/core").Type<any> | import("@angular/core").ModuleWithProviders<{}> {
  throw new Error('Function not implemented.');
}

