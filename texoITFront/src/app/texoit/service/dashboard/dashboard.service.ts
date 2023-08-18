import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const URL_TEXOAPI_MULTIPLE_WINNERS  = "https://tools.texoit.com/backend-java/api/movies?projection=years-with-multiple-winners";

const URL_TOP3_STUDIOS_WINNERS      = "https://tools.texoit.com/backend-java/api/movies?projection=studios-with-win-count";

const URL_MIN_MAX_WINS              = "https://tools.texoit.com/backend-java/api/movies?projection=max-min-win-interval-for-producers";

const URL_MOVIES_WINNERS_BY_YEAR    = "https://tools.texoit.com/backend-java/api/movies?winner=true&year=";

@Injectable({
  providedIn: 'root'
})
export class DashboardService
{
  private httpClient:HttpClient;

  constructor(httpClient:HttpClient)
  {
    this.httpClient = httpClient;
  }

  /**
   * @description Obtem multiplos ganhadores
   * @returns Observable<Object>
   */
  listYearsWithMultipleWinners():Observable<Object>
  {
    return this.httpClient.get(URL_TEXOAPI_MULTIPLE_WINNERS);
  }

  /**
   * @description Obtem os 3 primeiros ganhadores em ordem decrescente
   * @returns Observable<Object>
   */
  listTop3StudiosWithWinners():Observable<Object>
  {
    return this.httpClient.get(URL_TOP3_STUDIOS_WINNERS);
  }

  /**
   * @description obtem o ganhador com o mínimo e maximo de intervalo entre vitorias
   * @returns Observable<Object>
   */
  listMinAndMaxWinners():Observable<Object>
  {
    return this.httpClient.get(URL_MIN_MAX_WINS);
  }

  listMovieWinnerByYear(year:number):Observable<Object>
  {
    return this.httpClient.get(URL_MOVIES_WINNERS_BY_YEAR+year);
  }

} // DashboardService
