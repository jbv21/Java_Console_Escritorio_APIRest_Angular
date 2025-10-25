import { Routes } from '@angular/router';
import { PageNotfoundComponent } from './shared/components/page.notfound.component/page.notfound.component';
import { LoginComponent } from './public/login/component/login';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'home',
    loadChildren: () =>
      import('./features/home/router/home.routes').then((m) => m.HOME_ROUTES),
  },
  { 
    path: '', 
    redirectTo: '/login', 
    pathMatch: 'full' },

  { 
    path: '**', 
    component: PageNotfoundComponent 
  },
];
