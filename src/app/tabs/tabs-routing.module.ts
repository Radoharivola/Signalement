import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TabsPage } from './tabs.page';

const routes: Routes = [
  {
    path: 'tabs',
    component: TabsPage,
    children: [

      {
        path: 'details-notification',
        loadChildren: () => import('../pages/notifications/details-notification/details-notification.module').then(m => m.DetailsNotificationPageModule)
      },
      {
        path: 'list-notification',
        loadChildren: () => import('../pages/notifications/list-notification/list-notification.module').then(m => m.ListNotificationPageModule)
      },
      {
        path: 'list-signalement',
        loadChildren: () => import('../pages/signalement/list-signalement/list-signalement.module').then(m => m.ListSignalementPageModule)
      },
      {
        path: 'insert-signalement',
        loadChildren: () => import('../pages/signalement/insert-signalement/insert-signalement.module').then(m => m.InsertSignalementPageModule)
      },
      {
        path: 'details-signalement',
        loadChildren: () => import('../pages/signalement/details-signalement/details-signalement.module').then(m => m.DetailsSignalementPageModule)
      },
      {
        path: 'menu',
        loadChildren: () => import('../pages/menu/menu.module').then(m => m.MenuPageModule)
      },
      {
        path: '',
        redirectTo: '/tabs/list-signalement',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '',
    redirectTo: '/tabs/list-signalement',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
})
export class TabsPageRoutingModule { }
