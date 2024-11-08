import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-solicitud',
  templateUrl: './solicitud.component.html',
  styleUrl: './solicitud.component.scss'
})
export class SolicitudComponent implements OnInit{

  rol?: string ;
  ngOnInit(): void {
    this.rol=sessionStorage.getItem('rol')!;
    
    
  }

}
