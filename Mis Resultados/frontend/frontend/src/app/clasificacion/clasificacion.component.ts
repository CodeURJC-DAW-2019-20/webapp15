import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Team } from 'src/app/team';
import { HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-clasificacion',
  templateUrl: './clasificacion.component.html',
  styleUrls: ['./clasificacion.component.css']
})
export class ClasificacionComponent implements OnInit {
  public teams: Team[]=[];

  constructor(private http: HttpClient) {
    let url = environment.apiEndPoint + '/table/La liga';
    this.http.get(url).subscribe(
      response =>{
        let teamsAux : any = response;
        for(var i=0;i<teamsAux.length;i++){
          let teamName = teamsAux[i].name;
          this.teams.push(teamsAux[i]);
        }
      },
      error =>{
        console.error(error);
      }
    );
    }
  ngOnInit(): void {
  }

}
