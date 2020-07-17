import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ClasificacionComponent } from './clasificacion/clasificacion.component';
import { PartidosComponent } from './partidos/partidos.component';
import { EquiposComponent } from './equipos/equipos.component';
import { LoginComponent } from './login/login.component';
import { ApostarComponent } from './apostar/apostar.component';
import { EquipoComponent } from './equipo/equipo.component';
import { RegistroComponent } from './registro/registro.component';
import { UserComponent } from './user/user.component';


const routes: Routes = [
  {
    path:'',
    component: HomeComponent
  },
  {
    path:'home',
    component: HomeComponent
  },
  {
    path:'clasificacion',
    component: ClasificacionComponent
  },  
  {
    path:'partidos',
    component: PartidosComponent
  },
  {
    path:'equipos',
    component: EquiposComponent
  },  
  {
    path:'login',
    component: LoginComponent
  },
  {
    path:'apostar',
    component: ApostarComponent
  },
  {
    path:'equipo/:id',
    component: EquipoComponent
  },
  {
    path:'home',
    component: HomeComponent
  },
  {
    path:'registro',
    component: RegistroComponent
  },
  {
    path:'user',
    component: UserComponent
  }
  
];

export const routing = RouterModule.forRoot(routes);

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

