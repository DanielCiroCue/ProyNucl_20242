import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [{
  path: 'administracion',
  loadChildren: () => import('./administracion/administracion.module')
    .then(m => m.AdministracionModule)
}, {
  path: 'publico',
  loadChildren: () => import('./publico/publico.module')
    .then(m => m.PublicoModule)
}, {
  path: 'gestion',
  loadChildren: () => import('./gestion/gestion.module')
    .then(m => m.GestionModule)
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
