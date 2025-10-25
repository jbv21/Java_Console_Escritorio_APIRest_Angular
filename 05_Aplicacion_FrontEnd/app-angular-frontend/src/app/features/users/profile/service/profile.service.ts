import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ProfileResponse } from '../model/profile.response';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {

  private httpClient = inject(HttpClient);

  url = `${environment.API_USER}/v1/auth`;


  getProfile(): Observable<ProfileResponse> {
    return this.httpClient.get<ProfileResponse>(`${this.url}/profile`);
  }

}
