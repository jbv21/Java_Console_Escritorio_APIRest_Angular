import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from '../../../../shared/components/header/header.component';
import { NavBarComponent } from '../../../../shared/components/nav-bar/nav-bar.component';

@Component({
  selector: 'app-home.component',
  imports: [
    RouterOutlet,
    HeaderComponent,
    NavBarComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
