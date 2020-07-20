import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { LocalStorage } from 'ngx-webstorage';


@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

  public user;

  @LocalStorage('isUserLogged')
  public isUserLoggedIn;

  constructor(public userService: UserService, public router: Router, public loginService: LoginService) {
    this.createUser();
  }

  ngOnInit(): void {
    if(this.isUserLoggedIn){
      this.router.navigate(['/error']);
    }
  }
  private createUser() {
    this.user = new FormGroup({
      name: new FormControl(null, Validators.required),
      email: new FormControl(null, Validators.required),
      surname: new FormControl(null, Validators.required),
      roles: new FormControl(['ROLE_USER']),
      passwordHash: new FormControl(null, Validators.required),
    });
  }

  submit() {
      this.userService.saveUser(this.user.value).subscribe(
        (response) => {
          this.router.navigate(['/login'])
        },
        (error) => {
          if (error.status == 406) {
            alert("Usuario ya existe con ese UserName");
          }
          console.error(error);
        }
      );
  }
}
