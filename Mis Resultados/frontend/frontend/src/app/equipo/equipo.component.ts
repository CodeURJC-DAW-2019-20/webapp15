import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient} from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Team } from 'src/app/team';


@Component({
  selector: 'app-equipo',
  templateUrl: './equipo.component.html',
  styleUrls: ['./equipo.component.css']
})

export class EquipoComponent implements OnInit {

  team: Team;
  name: string;


  constructor(private http: HttpClient,private router: Router, activatedRoute: ActivatedRoute) {
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

}
