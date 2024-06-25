import { Router } from '@angular/router';
import { Auth } from '../../models/auth';
import { SharedModule } from '../../shared/shared.module';
import { AuthService } from './../quiz/service/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  imports: [SharedModule],
  standalone: true,
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  authenticate() {
    let auth: Auth = {
      email: this.email,
      password: this.password,
    };
    this.authService.authenticate(auth).subscribe(response => {{
      console.log(localStorage.getItem('token'));
      // vai para a rota principal da aplicação
      this.router.navigate(['']);
    }});
  }
}
