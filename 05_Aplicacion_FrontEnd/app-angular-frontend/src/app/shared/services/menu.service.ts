import { Injectable } from '@angular/core';
import { Menu } from '../models/menu';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private menuClass = 'open_menu_movil';

  constructor() { }

  
  private menuSections: Menu[] = [
    { label: 'Panel', route: '/dashboard' },
    {
      label: 'Escuela Conducción',
      children: [
        { label: 'Mantenimiento', route: '/ventas/pedidos' }
      ]
    },
  ];

  getMenuSections(): Menu[] {
    return this.menuSections;
  }

  toggleMenu(): void {
    document.body.classList.toggle(this.menuClass);
  }
}
