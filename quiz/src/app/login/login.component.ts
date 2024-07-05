import { Router } from '@angular/router';
import { Auth } from '../../models/auth';
import { SharedModule } from '../../shared/shared.module';
import { AuthService } from './../quiz/service/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../quiz/service/user.service';
import { ToastrService } from 'ngx-toastr';

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

  entrar = true;
  cadastrar = false;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {}

  authenticate() {
    let auth: Auth = {
      email: this.email,
      password: this.password,
    };
    this.authService.authenticate(auth).subscribe(
      (response) => {
        // vai para a rota principal da aplicação
        this.router.navigate(['']);
      },
      (error) => {
        this.toastr.error(
          'Você não tem acesso ao sistema, cadastra-se e tente novamente',
          'Erro ao tentar acessar'
        );
      }
    );
  }

  alteraEntrar() {
    this.cadastrar = !this.cadastrar;
    this.entrar = true;
  }

  alteraCadastrar() {
    this.entrar = !this.entrar;
    this.cadastrar = true;
  }

  cadastraUsuario() {
    let user: User = {
      email: this.email,
      password: this.password,
    };
    this.userService.saveUser(user).subscribe((resp) => {
      console.log(resp);
      this.toastr.success(
        'Cadastro realizado com sucesso!'
      );
    },
    (error) => {
      this.toastr.error(
        'Erro ao realizar o cadastro'
      );
    });
  }
}
