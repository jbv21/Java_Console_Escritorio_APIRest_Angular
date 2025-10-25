import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { EscuelaService } from '../../services/escuela.service';
import { UbicacionService } from '../../../ubicacion/services/ubicacion.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DepartamentoModel } from '../../../ubicacion/models/departamento.model';
import { DistritoModel } from '../../../ubicacion/models/distrito.model';
import { ProvinciaModel } from '../../../ubicacion/models/provincia.model';
import { emailValidator } from '../../../../shared/validators/email.validator';
import { phoneValidator } from '../../../../shared/validators/phone.validator';
import { EscuelaConduccionModel } from '../../models/escuelaConduccion.model';

@Component({
  selector: 'app-escuela.registro.component',
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './escuela.registro.component.html',
  styleUrl: './escuela.registro.component.css'
})
export class EscuelaRegistroComponent implements OnInit {

  private escuelaService = inject(EscuelaService);
  private ubicacionService = inject(UbicacionService);

  private router = inject(Router);
  private activatedRoute = inject(ActivatedRoute);

  private formBuilder = inject(FormBuilder);
  private toastr = inject(ToastrService);
  public tipoErr?: string;

  public departamentos!: DepartamentoModel[];
  public provincias!: ProvinciaModel[];
  public distritos!: DistritoModel[];

  submitted = false;
  id?: number;

  public escuelaConduccionForm = this.formBuilder.group({
    ruc: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(11)]],
    nombre: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(200)]],
    correo: ['', [Validators.required,  emailValidator()]],
    telefono: ['', [Validators.required, Validators.pattern(/^(\+51\s?)?9\d{8}$/)]],
    direccion: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
    idDepartamento: ['', [Validators.required]],
    idProvincia: ['', [Validators.required]],
    idDistrito: ['', [Validators.required]],
    estado: ['', [Validators.required]],
  });

  constructor() {
    this.activatedRoute.params.subscribe({
      next: (params) => {
        if (params['id']) {
          this.id = Number(params['id']);
          this.escuelaService.findById(this.id).subscribe({
            next: (res) => {
              if (res.estado == true && res.resultado){
                this.escuelaConduccionForm.controls['ruc'].setValue(res.resultado.ruc);
                this.escuelaConduccionForm.controls['nombre'].setValue(res.resultado.nombre);
                this.escuelaConduccionForm.controls['correo'].setValue(res.resultado.correo);
                this.escuelaConduccionForm.controls['telefono'].setValue(res.resultado.telefono);
                this.escuelaConduccionForm.controls['direccion'].setValue(res.resultado.direccion);
                this.escuelaConduccionForm.controls['estado'].setValue(res.resultado.estado);

                let idDepartamento:string = String(res.resultado.ubigeo.id?.idDepartamento);
                let idProvincia:string = String(res.resultado.ubigeo.id?.idProvincia);
                let idDistrito:string = String(res.resultado.ubigeo.id?.idDistrito);

                this.cargarProvincia(idDepartamento);
                this.cargarDistrito(idDepartamento, idProvincia);

                this.escuelaConduccionForm.controls['idDepartamento'].setValue(idDepartamento);
                this.escuelaConduccionForm.controls['idProvincia'].setValue(idProvincia);
                this.escuelaConduccionForm.controls['idDistrito'].setValue(idDistrito);

              }

            },
            error(err){},
            complete(){},
          });


        }
      },
    });
  }

  ngOnInit(): void {
    this.cargarDepartamento();

        this.escuelaConduccionForm.controls['idDepartamento'].valueChanges.subscribe({
      next:(value)=> {
            this.cargarProvincia(String(value));
      }
    });
    
    this.escuelaConduccionForm.controls['idProvincia'].valueChanges.subscribe({
      next:(value)=> {
          let idDepartamento:string = String(this.escuelaConduccionForm.controls['idDepartamento'].value);
          this.cargarDistrito(idDepartamento, String(value));
      }
    });
  }


  cargarDepartamento() {
    this.ubicacionService.findAllDepartamento().subscribe({
      next: (res) => {
        this.departamentos = res.resultado;
      },
      error(err) {},
      complete() {},
    });
  }

  cargarProvincia(idDepartamento:string) {
    this.provincias = [];
    this.distritos = [];
    let obj: ProvinciaModel = <ProvinciaModel>{idDepartamento:idDepartamento};
    this.ubicacionService.findAllPronvicia(obj).subscribe({
      next: (res) => {
        this.provincias = res.resultado;
      },
      error(err) {},
      complete() {},
    });
  }

  cargarDistrito(idDepartamento:string, idProvincia:string) {
    this.distritos = [];
    let obj: DistritoModel = <DistritoModel>{idDepartamento:idDepartamento, idProvincia:idProvincia};
    this.ubicacionService.findAllDistrito(obj).subscribe({
      next: (res) => {
        this.distritos = res.resultado;
      },
      error(err) {},
      complete() {},
    });
  }

  save(){
    this.submitted = true;

    if(this.escuelaConduccionForm.invalid){
      return;
    }

    let escuelaConduccion: EscuelaConduccionModel = {
      ruc: this.fe['ruc'].value,
      nombre: this.fe['nombre'].value,
      correo: this.fe['correo'].value,
      telefono: this.fe['telefono'].value,
      estado: this.fe['estado'].value,
      direccion: this.fe['direccion'].value,
      ubigeo: {
        id: {
          idDepartamento:this.fe['idDepartamento'].value,
          idProvincia:this.fe['idProvincia'].value,
          idDistrito:this.fe['idDistrito'].value
        }
      } 
    };

    if(this.id){
      escuelaConduccion.id = this.id;
      this.escuelaService.update(escuelaConduccion).subscribe({
        next: (res) => {
          this.toastr.success(
            'La Escuela de Conducción fue actualizado con exito, id = ' + res.resultado.id
          );
          this.tipoErr = '';
          this.cancel();
        },
        error: (err) => {
          console.error(err);
          this.toastr.error('Error al actualizar el registro');
        },
      });
    } else {
      this.escuelaService.save(escuelaConduccion).subscribe({
        next: (res) => {
          this.toastr.success(
            'La Escuela de Conducción fue agregado con exito, id = ' + res.resultado.id
          );
          this.tipoErr = '';
          this.cancel();
        },
        error: (err) => {
          console.error(err);
          this.toastr.error('Error al agregar el registro');
        },
      });
    }
  }

  cancel() {
    this.router.navigate(['home/escuela']);
  }

  get fe(): { [key: string]: AbstractControl } {
    return this.escuelaConduccionForm.controls;
  }

}
