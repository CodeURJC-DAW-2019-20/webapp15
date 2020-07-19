import { Component, OnInit } from '@angular/core';
import { LocalStorage } from 'ngx-webstorage';
import { LoginComponent } from '../login/login.component';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @LocalStorage('isUserLogged')
  public isUserLoggedIn;

  @LocalStorage('currentUser')
  public currentUser;

  constructor(  public loginService: LoginService,
    public router: Router,
    ) {
     }

  ngOnInit(): void {
  }
  logOut(){
    this.loginService.logOut().subscribe(
      (response)=>
        this.router.navigate(['/'])
    );
  }

}
