import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { ClasificacionComponent } from './clasificacion/clasificacion.component';
import { PartidosComponent } from './partidos/partidos.component';
import { EquiposComponent } from './equipos/equipos.component';
import { EquipoComponent } from './equipo/equipo.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { RegistroComponent } from './registro/registro.component';
import { ApostarComponent } from './apostar/apostar.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HomeComponent,
    ClasificacionComponent,
    PartidosComponent,
    EquiposComponent,
    EquipoComponent,
    LoginComponent,
    UserComponent,
    RegistroComponent,
    ApostarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FontAwesomeModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
