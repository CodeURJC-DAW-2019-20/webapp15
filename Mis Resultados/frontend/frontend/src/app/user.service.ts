import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment'
import { HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { UserComponent } from 'src/app/user/user.component';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient,) {

  }
  register() {

  }
  login(user: string, pass: string){
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
  getUsers() {
    let url = environment.apiEndPoint + '/users/'
  }
  getUser(id) {
    let url = environment.apiEndPoint + '/user/' + id;
   }


}
