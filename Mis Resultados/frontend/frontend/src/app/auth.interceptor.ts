import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LocalStorageService } from 'ngx-webstorage';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private localStorage: LocalStorageService) {}
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("Inicio AuthInterceptor");
    console.log("Request:");
    console.log(request);
    let localStore: LocalStorageService;

    let user = this.localStorage.retrieve('currentUser');
    console.log("User authInterceptor");
    console.log(user);
    if (user && user.authData){
      console.log("hola")
      request = request.clone({
        setHeaders: {
          Authorization: "Basic " + user.authData,
        }  
      });
    }
    console.log("Fin AuthInterceptor");
    return next.handle(request);
  }
}
