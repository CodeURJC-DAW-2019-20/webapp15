import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service'
import { Router } from '@angular/router';
import {LoginService} from 'src/app/login.service';
import { LocalStorage } from 'ngx-webstorage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user:string;
  public pass:string;
  @LocalStorage('isUserLogged')
  public isUserLoggedIn;


  constructor(public userService: UserService, public loginService: LoginService, public router: Router){}

  ngOnInit(): void {
    if(this.isUserLoggedIn){
      this.router.navigate(['/equipos']);
    }
  }
  login(){
    console.log("Inicio logIn login.Component");
    console.log("Login.component isLoggedIn"+this.isUserLoggedIn);
    this.loginService.login(this.user, this.pass).subscribe(
      (user) => {
        this.router.navigate(['/']);
        console.log("Login.component User:");
        console.log(user)
      },
      (error) => {
        console.error(error);
        alert('Invalid user or password');
      }
    );
    console.log("Login.component isLoggedIn"+this.isUserLoggedIn);

  }

  cancel(){
    this.router.navigate(['/']);
  }


}
