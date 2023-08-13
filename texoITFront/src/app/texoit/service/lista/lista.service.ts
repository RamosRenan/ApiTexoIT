import { HttpClient, HttpHandler } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subscriber } from 'rxjs';


const URL_TEXOAPI = "https://tools.texoit.com/backend-java/api/movies?page=1&size=2&";

const DEFAULT_FILTERS_MOVIES = "winner=false&year=2018";

@Injectable({
  providedIn: 'root'
})
export class ListaService
{
  private httpClient:HttpClient;

  private year:number = 0;

  private winner:string = "";

  constructor(httpClient:HttpClient)
  {
    this.httpClient = httpClient;
  }

  public listAndPeageMovies():Observable<Object>
  {
    return this.httpClient.get(URL_TEXOAPI+DEFAULT_FILTERS_MOVIES);
  }

  public listMoviesByFiltersYear(year:number):Observable<Object>
  {
    this.year = year;

    if (this.winner == "")
      this.winner = "false";

    return this.httpClient.get(URL_TEXOAPI+"winner="+this.winner+"&"+"year="+this.year);
  }

  public listMoviesByFiltersWinner(winner:string):Observable<Object>
  {
    this.winner = winner;

    if (this.year == 0)
      this.year = 2018;

    return this.httpClient.get(URL_TEXOAPI+"winner="+(winner == "yes" ? "true" : "false")+"&"+"year="+this.year);
  }

}// class ListaService
