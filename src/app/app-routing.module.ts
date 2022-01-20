import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListSignalementComponent } from './pages/body/list-signalement/list-signalement.component';
import { AdminComponent } from './pages/body/admin/admin.component';
import { RegionComponent } from './pages/body/region/region.component';
import { TypeProblemComponent } from './pages/body/type-problem/type-problem.component';

const routes: Routes = [
  {path: 'list', component: ListSignalementComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'region', component:RegionComponent},
  {path:'typeproblem',component:TypeProblemComponent},
  {path:'admin',component:AdminComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
