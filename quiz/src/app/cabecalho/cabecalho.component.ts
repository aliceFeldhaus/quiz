import { AuthService } from './../quiz/service/auth.service';
import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cabecalho',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './cabecalho.component.html',
  styleUrl: './cabecalho.component.scss',
})
export class CabecalhoComponent {

  constructor(private authService: AuthService, private router: Router,
    private toastr: ToastrService
  ) {}

  logout() {
    this.authService.logout();
    this.router.navigate(['login']);
    this.toastr.info('Mas já? Volte sempre!', 'Logout');
  }
}
