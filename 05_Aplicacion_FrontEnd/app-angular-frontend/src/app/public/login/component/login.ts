import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from '../model/login.request';
import { CommonModule } from '@angular/common';
import { LoginService } from '../services/login.service';
import { LoginResponse } from '../model/login.response';

@Component({
  selector: 'app-login',
  imports: [
  CommonModule,
  ReactiveFormsModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class LoginComponent {

  private router=inject(Router)
  private formBuilder = inject(FormBuilder);
  private loginService = inject(LoginService)

  public loginForm = this.formBuilder.group({
    email: [
      'maria@mail.com',
      //[Validators.required],
    ],
    password: [
      '12345',
      //[Validators.required],
    ],
  });

  // email: john@mail.com
  // password:changeme

  login(){

    console.log('login...')

    const loginRequest:LoginRequest= new LoginRequest(
      this.loginForm.controls['email'].value || '',
      this.loginForm.controls['password'].value || ''
    )

    console.log(loginRequest)

    this.loginService.login(loginRequest).subscribe({
      next:(res:LoginResponse) =>{
          console.log(res)
          this.router.navigate(['home'])
          sessionStorage.setItem('access_token',res.access_token)
          sessionStorage.setItem('refresh_token',res.refresh_token)
      },
      error(err) {

      },
    })



  }

}
