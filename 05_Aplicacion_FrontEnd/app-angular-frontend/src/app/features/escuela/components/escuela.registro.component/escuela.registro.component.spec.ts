import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EscuelaRegistroComponent } from './escuela.registro.component';

describe('EscuelaRegistroComponent', () => {
  let component: EscuelaRegistroComponent;
  let fixture: ComponentFixture<EscuelaRegistroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EscuelaRegistroComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EscuelaRegistroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
