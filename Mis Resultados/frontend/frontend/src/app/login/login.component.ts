import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user:string;
  public pass:string;
   
  constructor(public userService: UserService){

  }
  ngOnInit(): void {
  }

  login(){
    this.userService.login(this.user,this.pass).subscribe(
      user=>{console.log(user)})
  }
}
