import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient,HttpHeaders} from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Team } from 'src/app/team';
import { LocalStorage } from 'ngx-webstorage';
import { LoginComponent } from '../login/login.component';
import { LoginService } from '../login.service';


@Component({
  selector: 'app-equipo',
  templateUrl: './equipo.component.html',
  styleUrls: ['./equipo.component.css']
})

export class EquipoComponent implements OnInit {

  team: Team;
  name: string;


  @LocalStorage('currentUser')
  public currentUser;
  @LocalStorage('isUserLogged')
  public isUserLoggedIn;

  constructor(private http: HttpClient,private router: Router, activatedRoute: ActivatedRoute,loginService: LoginService) {
    let id = activatedRoute.snapshot.params['id'];
    let url = environment.apiEndPoint + '/equipos/'+id;
    this.http.get<Team>(url).subscribe(
      response => {
        let teamAux : Team = response;
        this.team = teamAux;
      },
      error =>{
        console.error(error);
      }
    )
   }

  ngOnInit(): void {
  }

  getTeamData(id : any, url: string){
    
    return this.http.get<Team>(url).pipe(map(team => team));
  }
  updateFav(teamName:string){
    let url = environment.apiEndPoint + '/user/updateFav/'+this.currentUser.name+'/'+teamName;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    this.http.put<Team>(url,headers).subscribe(
      response => {
      },
      error =>{
        console.error(error);
      }
    )
  }

}
