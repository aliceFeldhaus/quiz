import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginator,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
  ],
  exports: [
    CommonModule,
    MatTableModule,
    MatPaginator,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
  ],
})
export class SharedModule {}
