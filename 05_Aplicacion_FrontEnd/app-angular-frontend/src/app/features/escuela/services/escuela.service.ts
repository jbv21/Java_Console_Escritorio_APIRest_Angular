import { inject, Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { Observable } from 'rxjs';
import { ApiResponseModel } from '../../../shared/models/apiReponde.model';
import { EscuelaConduccionModel } from '../models/escuelaConduccion.model';
import { HttpClient } from '@angular/common/http';
import { PagesModel } from 'ngx-bootstrap/pagination';
import { PageModel } from '../../../shared/models/page.model';

@Injectable({
  providedIn: 'root'
})
export class EscuelaService {

  url = `${environment.API_BASE}/api/escuelas`;
  
  private httpClient = inject(HttpClient);

  findAll(obj: EscuelaConduccionModel): Observable<ApiResponseModel<EscuelaConduccionModel[]>> {
    return this.httpClient.get<ApiResponseModel<EscuelaConduccionModel[]>>(
      `${this.url}?idDepartamento=${obj?.ubigeo?.id?.idDepartamento}&idProvincia=${obj?.ubigeo?.id?.idProvincia}&idDistrito=${obj?.ubigeo?.id?.idDistrito}&ruc=${obj?.ruc}`
    );
  }
  
  findAllCustomPaging(page: number, size: number, obj: EscuelaConduccionModel)
    : Observable<ApiResponseModel<PageModel<EscuelaConduccionModel>>> {
  return this.httpClient.get<ApiResponseModel<PageModel<EscuelaConduccionModel>>>(
    `${this.url}/paging?page=${page}&size=${size}&idDepartamento=${obj?.ubigeo?.id?.idDepartamento}&idProvincia=${obj?.ubigeo?.id?.idProvincia}&idDistrito=${obj?.ubigeo?.id?.idDistrito}&ruc=${obj?.ruc}`
  );
}

  findById(id: number): Observable<ApiResponseModel<EscuelaConduccionModel>> {
    return this.httpClient.get<ApiResponseModel<EscuelaConduccionModel>>(`${this.url}/${id}`);
  }

  save(obj: EscuelaConduccionModel): Observable<ApiResponseModel<EscuelaConduccionModel>> {
    return this.httpClient.post<ApiResponseModel<EscuelaConduccionModel>>(this.url, obj);
  }

  updateEstado(obj: EscuelaConduccionModel): Observable<ApiResponseModel<EscuelaConduccionModel>> {
    return this.httpClient.patch<ApiResponseModel<EscuelaConduccionModel>>(`${this.url}/${obj.id}`, obj);
  }

  update(obj: EscuelaConduccionModel): Observable<ApiResponseModel<EscuelaConduccionModel>> {
    return this.httpClient.put<ApiResponseModel<EscuelaConduccionModel>>(`${this.url}/${obj.id}`, obj);
  }

  delete(id: number): Observable<ApiResponseModel<null>> {
    return this.httpClient.delete<ApiResponseModel<null>>(`${this.url}/${id}`);
  }
  
}
