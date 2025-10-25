import { CommonModule, UpperCasePipe } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { PageChangedEvent, PaginationModule } from 'ngx-bootstrap/pagination';
import { EscuelaService } from '../../services/escuela.service';
import { UbicacionService } from '../../../ubicacion/services/ubicacion.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { EscuelaConduccionModel } from '../../models/escuelaConduccion.model';
import { DepartamentoModel } from '../../../ubicacion/models/departamento.model';
import { ProvinciaModel } from '../../../ubicacion/models/provincia.model';
import { DistritoModel } from '../../../ubicacion/models/distrito.model';
import { UbigeoIdModel, UbigeoModel } from '../../../ubicacion/models/ubigeo.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-escuela.listado.component',
  imports: [
    CommonModule,
    UpperCasePipe,
    ReactiveFormsModule,
    PaginationModule
  ],
  templateUrl: './escuela.listado.component.html',
  styleUrl: './escuela.listado.component.css'
})
export class EscuelaListadoComponent implements OnInit {

  private escuelaService = inject(EscuelaService);
  
  private ubicacionService = inject(UbicacionService);

  private router = inject(Router);

  private toastr = inject(ToastrService);
  
  private escuelasConduccion!: EscuelaConduccionModel[];

  public departamentos!: DepartamentoModel[];
  public provincias!: ProvinciaModel[];
  public distritos!: DistritoModel[];

  public totalItems:number=0;
  public readonly MAX_SIZE:number=5;
  public readonly ITEMS_SIZE:number=10;
  public itemsPage!: EscuelaConduccionModel[];
  public page?: number=1;
  
  private formBuilder = inject(FormBuilder);

  public escuelaConduccionListadoForm = this.formBuilder.group({
    ruc: [''],
    departamento:[''],
    provincia:[''],
    distrito:['']
  });

  ngOnInit(): void {
    this.buscar();
    this.cargarDepartamento();

    this.escuelaConduccionListadoForm.controls['departamento'].valueChanges.subscribe({
      next:(value)=> {
            this.cargarProvincia(String(value));
      }
    });
    
    this.escuelaConduccionListadoForm.controls['provincia'].valueChanges.subscribe({
      next:(value)=> {
          let idDepartamento:string = String(this.escuelaConduccionListadoForm.controls['departamento'].value);
          this.cargarDistrito(idDepartamento, String(value));
      }
    });


  }

  nuevo() {
    this.router.navigate(['home/escuela/registro']);
  }

  limpiar() {
    this.escuelaConduccionListadoForm.controls['ruc'].setValue('');
    this.escuelaConduccionListadoForm.controls['departamento'].setValue('');
    this.escuelaConduccionListadoForm.controls['provincia'].setValue('');
    this.escuelaConduccionListadoForm.controls['distrito'].setValue('');
    this.buscar();
  }

  buscar(){
    let escuelaConduccionFiltro: EscuelaConduccionModel = <EscuelaConduccionModel>{};
    let ubigeoIdFiltro: UbigeoIdModel = <UbigeoIdModel>{idDepartamento: "",idProvincia:"", idDistrito:""};
    escuelaConduccionFiltro.ruc = this.escuelaConduccionListadoForm.controls['ruc'].value || '';
    ubigeoIdFiltro.idDepartamento = this.escuelaConduccionListadoForm.controls['departamento'].value || '';
    ubigeoIdFiltro.idProvincia = this.escuelaConduccionListadoForm.controls['provincia'].value || '';
    ubigeoIdFiltro.idDistrito = this.escuelaConduccionListadoForm.controls['distrito'].value || '';
    escuelaConduccionFiltro.ubigeo = <UbigeoModel>{id:ubigeoIdFiltro};

    this.escuelaService.findAll(escuelaConduccionFiltro).subscribe({
    next: (res) => {
        if(res.estado == true && res.resultado){
          this.escuelasConduccion = res.resultado;
          this.totalItems=this.escuelasConduccion.length
          this.itemsPage=this.escuelasConduccion.slice(0,this.ITEMS_SIZE)
        } else {
          this.totalItems=0
          this.itemsPage=[]
        }
      },
      error(err) {},
      complete() {},
    });
  }

  edit(escuelaConduccion: EscuelaConduccionModel) {
    this.router.navigate(['home/escuela/registro', escuelaConduccion.id]);
  }

  delete(escuelaConduccion: EscuelaConduccionModel) {
    Swal.fire({
      title: 'Alerta',
      text: `Confirma la eliminación de la Escuela: ${escuelaConduccion.nombre}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si',
      cancelButtonText: 'No',
    }).then((result) => {
      if (result.isConfirmed) {
        let id: number = (escuelaConduccion.id)?escuelaConduccion.id:0;
        this.escuelaService.delete(id).subscribe({
          next: (res) => {
            Swal.fire({
              title: 'Aviso',
              text: `La Escuela de Conducción ${escuelaConduccion.nombre}, fue eliminado con éxito`,
              icon: 'success',
            });
            this.escuelasConduccion = this.escuelasConduccion.filter((e) => e.id != escuelaConduccion.id);
          },
          error: (err) => {
            this.toastr.error(
              `Error al eliminar la escuela de conducción con el id = ${escuelaConduccion.id}`
            );
          },
        });
      }
    });
  }

 pageChanged(event: PageChangedEvent): void {
    this.page = event.page;
    const start= (this.page-1)*(this.ITEMS_SIZE)
    const end= (this.page)*(this.ITEMS_SIZE)
    this.itemsPage=this.escuelasConduccion.slice(start,end)
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



}
