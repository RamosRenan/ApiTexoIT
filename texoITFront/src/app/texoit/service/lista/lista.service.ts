import { HttpClient, HttpHandler, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

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

  /**
   * @description Ao acessar a tela de listagem pela primeira vez,
   * o usuário terá acesso a uma lista padrão
   *
   * @returns Observable< any>
   */
  public listAndPeageMovies():Observable< any>
  {
    console.log(">>>url: "+URL_TEXOAPI+DEFAULT_FILTERS_MOVIES);

    return this.httpClient.get(URL_TEXOAPI+DEFAULT_FILTERS_MOVIES);
  }

  /**
   * @description Lista filmes por ano
   *
   * @returns Observable< any>
   */
  public listMoviesByFiltersYear(year:number):Observable< any>
  {
    this.year = year;

    if (this.winner == "")
      this.winner = "false";

    return this.httpClient.get(URL_TEXOAPI+"winner="+this.winner+"&"+"year="+this.year);
  }

  /**
   * @description Lista filmes por vencedores
   *
   * @returns Observable< any>
   */
  public listMoviesByFiltersWinner(winner:string):Observable< any>
  {
    this.winner = winner;

    if (this.year == 0)
      this.year = 2018;

    return this.httpClient.get(URL_TEXOAPI+"winner="+(winner == "yes" ? "true" : "false")+"&"+"year="+this.year);
  }

}// class ListaService
