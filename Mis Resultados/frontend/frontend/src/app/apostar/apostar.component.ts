import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Match } from 'src/app/match';
import { LocalStorage } from 'ngx-webstorage';

@Component({
  selector: 'app-apostar',
  templateUrl: './apostar.component.html',
  styleUrls: ['./apostar.component.css']
})
export class ApostarComponent implements OnInit {

  public nextMatches: Match[] = [];
  public betMatches: Match[] = [];
  public codigoHtmlInicio: boolean = false;
  public totalBet;

  @LocalStorage('currentUser')
  public currentUser;

  constructor(private http: HttpClient) {
    let url = environment.apiEndPoint + '/equipos/nextmatches';
    this.http.get<Match[]>(url).subscribe(
      response => {
        let matchAux: any = response;
        for (var i = 0; i < matchAux.length; i++) {
          this.nextMatches.push(matchAux[i]);
        }
      },
      error => {
        console.error(error);
      }
    )
    if (this.betMatches.length != 0) {
      this.codigoHtmlInicio = true;
    }
  }

  ngOnInit(): void {
  }

  selectBet(local: string, visit: string, betNumber: string, betSelect: string) {
    let url = environment.apiEndPoint + '/apostar/' + local + '/' + visit + '/' + betNumber + '/' + betSelect;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    this.http.post<Match[]>(url, { headers }).subscribe(
      response => {
        console.log(response);
        this.betMatches = response;
      },
      error => {
        console.error(error);
      }
    )
    this.codigoHtmlInicio = true;
  }
  calculateBet(prize: number) {
    let url = environment.apiEndPoint + '/apostar/calcularTotal/'+prize+'/';
    console.log(url);
    this.http.get<any>(url).subscribe(
      response => {
        console.log(response);
        this.totalBet=response;
      },
      error => {
        console.error(error);
      }
    )
  }
  deleteBet() {
    let url = environment.apiEndPoint + '/apostar/deleteBet/';
    console.log(url);
    this.http.delete<any>(url).subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.error(error);
      }
    )
    this.codigoHtmlInicio=false;
    this.betMatches = [];

  }
  doBet(prize: number){
    let url = environment.apiEndPoint + '/apostar/doBet/'+prize+'/'+this.currentUser.name;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    this.http.put<any>(url,headers).subscribe(
      response => {
        console.log(response);

      },
      error => {
        console.error(error);
      }
    )
    this.codigoHtmlInicio=false;
    this.betMatches = [];
  }

}
