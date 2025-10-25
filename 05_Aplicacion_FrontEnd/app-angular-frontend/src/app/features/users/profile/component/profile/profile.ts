import { Component, inject, OnInit } from '@angular/core';
import { ProfileService } from '../../service/profile.service';
import { ToastrService } from 'ngx-toastr';
import { ProfileResponse } from '../../model/profile.response';

@Component({
  selector: 'app-profile',
  imports: [],
  templateUrl: './profile.html',
  styleUrl: './profile.css'
})
export class ProfileComponent implements OnInit {

  private profileService = inject(ProfileService);

  private toastr = inject(ToastrService);

  profile!:ProfileResponse

  ngOnInit(): void {
    this.getProfile()
  }


  getProfile() {

    this.profileService.getProfile().subscribe({
      next: (res) => {
        this.profile=res
        console.log(this.profile)
      },
      error: (err) => this.toastr.error('Error al obtener el profile', 'Error'),
    });
  }

}
