import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';

@Component({
  selector: 'app-cabecalho',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './cabecalho.component.html',
  styleUrl: './cabecalho.component.scss'
})
export class CabecalhoComponent {

}
