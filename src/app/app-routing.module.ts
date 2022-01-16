import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListSignalementComponent } from './pages/body/list-signalement/list-signalement.component';
import { AdminComponent } from './pages/body/admin/admin.component';
import { AuthComponent } from './pages/auth/auth.component';
import { MapComponent } from './pages/body/map/map.component';

const routes: Routes = [
  { path: 'list', component: ListSignalementComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'login', component: AuthComponent },
  { path:'map',component:MapComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
