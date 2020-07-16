import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { ConstantPool } from '@angular/compiler';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() {}
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("hola");
    let user = JSON.parse(localStorage.getItem('currentUser'))

    if (user && user.authData){
      request = request.clone({
        setHeaders: {
          Authorization: "Basic " + user.authData,
        }  
      });
    }
    return next.handle(request);
  }
}
