import { Component, OnInit } from '@angular/core';
import { HttpClient,HttpHeaders} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Team } from 'src/app/team';
import { Match } from 'src/app/match';

@Component({
  selector: 'app-partidos',
  templateUrl: './partidos.component.html',
  styleUrls: ['./partidos.component.css']
})
export class PartidosComponent implements OnInit {

  public nextMatches: Match[]=[];

  constructor(private http: HttpClient) {
    let url = environment.apiEndPoint + '/equipos/nextmatches';
    this.http.get<Match>(url).subscribe(
      response => {
        let matchAux : any = response;
        for(var i=0;i<matchAux.length;i++){
          this.nextMatches.push(matchAux[i]);
        }
      },
      error =>{
        console.error(error);
      }
    )
   }

  ngOnInit(): void {
  }

  addMatch(local:string,visitante:string){
      let url = environment.apiEndPoint + '/equipos/addMatch/'+local+'/'+visitante;
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
      });

      this.http.post<Team>(url,headers).subscribe(
        response => {
          console.log(response);
        },
        error =>{
          console.error(error);
        }
      )
  
  }

}
