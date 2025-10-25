import { inject, Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DepartamentoModel } from '../models/departamento.model';
import { ApiResponseModel } from '../../../shared/models/apiReponde.model';
import { ProvinciaModel } from '../models/provincia.model';
import { DistritoModel } from '../models/distrito.model';

@Injectable({
  providedIn: 'root'
})
export class UbicacionService {

  url = `${environment.API_BASE}/api/ubicacion/`;
  
  private httpClient = inject(HttpClient);

  findAllDepartamento(): Observable<ApiResponseModel<DepartamentoModel[]>> {
    return this.httpClient.get<ApiResponseModel<DepartamentoModel[]>>(this.url+"departamentos");
  }
  
  findAllPronvicia(obj:ProvinciaModel): Observable<ApiResponseModel<ProvinciaModel[]>> {
    console.log(obj);
    return this.httpClient.get<ApiResponseModel<ProvinciaModel[]>>(this.url+"provincias/"+obj.idDepartamento);
  }

  findAllDistrito(obj:DistritoModel): Observable<ApiResponseModel<DistritoModel[]>> {
    return this.httpClient.get<ApiResponseModel<DistritoModel[]>>(this.url+"distritos/"+obj.idDepartamento+"/"+obj.idProvincia);
  }

}
