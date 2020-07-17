import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-equipos',
  templateUrl: './equipos.component.html',
  styleUrls: ['./equipos.component.css']
})
export class EquiposComponent implements OnInit {
  public teams : any[]=[];
  public imgs = new Map();
  constructor(private http: HttpClient) {
    let url = environment.apiEndPoint + '/equipos';
    this.http.get(url).subscribe(
      response =>{
        let teamsAux : any = response;
        for(var i=0;i<teamsAux.length;i++){
          let teamName = teamsAux[i].name;
          this.imgs.set(teamName,"../../assets/"+teamsAux[i].nameImgShield+".jpg");
          this.teams.push(teamsAux[i]);
        }
      },
      error =>{
        console.error(error);
      }
    );
    console.log(this.teams);
   }

  ngOnInit(): void {
  }
  search(name : string){
    return this.imgs.get(name);
  }

}
