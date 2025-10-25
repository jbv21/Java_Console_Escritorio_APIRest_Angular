import { Routes } from '@angular/router';
import { HomeComponent } from '../components/home.component/home.component';

export const HOME_ROUTES: Routes = [

  {
    path: '',
    component: HomeComponent,
    children:[
        {
          path:'dashboard',
          loadComponent: () =>import('../../dashboard/components/dashboard/dashboard.component').then(c => c.DashboardComponent)
        },

        {
          path: 'escuela',
          loadComponent: () =>import('../../escuela/components/escuela.listado.component/escuela.listado.component').then(c=>c.EscuelaListadoComponent)
        },
        {
          path: 'escuela/registro',
          loadComponent: () =>import('../../escuela/components/escuela.registro.component/escuela.registro.component').then(c=>c.EscuelaRegistroComponent)
        },
        {
          path: 'escuela/registro/:id',
           loadComponent: () =>import('../../escuela/components/escuela.registro.component/escuela.registro.component').then(c=>c.EscuelaRegistroComponent)
        },

        {
          path:'profile',
          loadComponent: () =>import('../../users/profile/component/profile/profile').then(c=>c.ProfileComponent)
        },

        { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
    ]
  },

];
