import { LoginRequest } from './../model/login.request';
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment.development';
import { LoginResponse } from '../model/login.response';


@Injectable({
  providedIn: 'root',
})
export class LoginService {
  url = `${environment.API_USER}/v1/auth`;
  private httpClient = inject(HttpClient);

  login(LoginRequest:LoginRequest): Observable<LoginResponse> {
    return this.httpClient.post<LoginResponse>(`${this.url}/login`,LoginRequest);
  }

}
