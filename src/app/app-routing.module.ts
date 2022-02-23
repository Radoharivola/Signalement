import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  // {
  //   path: '',
  //   loadChildren: () => import('./pages/auth/login/login.module').then( m => m.LoginPageModule)
  // },
  {
    path: '',
    loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
  {
    path: 'register',
    loadChildren: () => import('./pages/auth/register/register.module').then( m => m.RegisterPageModule)
  },
  {
    path: 'login',
    loadChildren: () => import('./pages/auth/login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'details-notification',
    loadChildren: () => import('./pages/notifications/details-notification/details-notification.module').then( m => m.DetailsNotificationPageModule)
  },
  {
    path: 'list-notification',
    loadChildren: () => import('./pages/notifications/list-notification/list-notification.module').then( m => m.ListNotificationPageModule)
  },
  {
    path: 'list-signalement',
    loadChildren: () => import('./pages/signalement/list-signalement/list-signalement.module').then( m => m.ListSignalementPageModule)
  },
  {
    path: 'insert-signalement',
    loadChildren: () => import('./pages/signalement/insert-signalement/insert-signalement.module').then( m => m.InsertSignalementPageModule)
  },
  {
    path: 'details-signalement',
    loadChildren: () => import('./pages/signalement/details-signalement/details-signalement.module').then( m => m.DetailsSignalementPageModule)
  },
  {
    path: 'menu',
    loadChildren: () => import('./pages/menu/menu.module').then( m => m.MenuPageModule)
  },

];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
