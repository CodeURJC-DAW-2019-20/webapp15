import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { User } from 'src/app/user';
import { throwError, Observable } from 'rxjs';
import { catchError}  from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {

  }
  register() {

  }
  login(user: string, pass: string) {
    let auth = window.btoa(user + ':' + pass);
    let url = environment.apiEndPoint + '/logIn'

    const headers = new HttpHeaders({
      Authorization: 'Basic ' + auth,
      'X-Requested-With': 'XMLHttpRequest',
    });

    return this.http.get<User>(url, { headers })
      .pipe(map(user => {
        console.log(user);
        return user;
      }));
  }
  getUsers(){
    const url = environment.apiEndPoint + '/users'

    return this.http.get(url)
      .pipe(
        map(user =>{
          return user;
        }),
        catchError(error => {
          return throwError(error);
        })
      );
  }
  getUser(id) {
    let url = environment.apiEndPoint + '/user/' + id;
  }
  saveUser(user){
    const body = JSON.stringify(user);
    console.log(body);
    const headers = new HttpHeaders({
        'Content-Type': 'application/json',
    });

    const url = environment.apiEndPoint + '/user/addUser'

    return this.http.post(url, user,{headers})
    .pipe(
      map(user =>{
        return user;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }
  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error);
  }

}