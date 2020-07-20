import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { LocalStorageService } from 'ngx-webstorage';
import { LocalStorage } from 'ngx-webstorage';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  @LocalStorage('currentUser')
  public currentUser;
  
  constructor() { }

  ngOnInit(): void {

  }

}
