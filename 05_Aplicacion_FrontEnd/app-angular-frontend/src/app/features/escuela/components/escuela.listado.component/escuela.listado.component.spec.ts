import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EscuelaListadoComponent } from './escuela.listado.component';

describe('EscuelaListadoComponent', () => {
  let component: EscuelaListadoComponent;
  let fixture: ComponentFixture<EscuelaListadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EscuelaListadoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EscuelaListadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
