import { Component } from '@angular/core';
import { PaginationInstance } from 'ngx-pagination/public-api';
import { ListaService } from '../service/lista/lista.service';
import { ListaInterface } from '../interfaces/lista/lista.interface';
import { HttpResponse } from '@angular/common/http';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent
{
  // variavel utilizada no html para paginação
  pagination:number = 1;

  itemsPerPage:number = 10;

  config:PaginationInstance = {itemsPerPage:2, currentPage:1};

  listMovies!:ListaInterface[];

  /**
   * @description constructor
   */
  constructor(private listaService:ListaService){}

  ngOnInit()
  {
    this.loadData();
  }// ngOnInit

  /**
   * @description responsavel por obter lista de files default
   */
  public loadData()
  {
    this.listaService.listAndPeageMovies().subscribe((values:any)=>{
      this.showModalAlert(values);

      // converte Object vindo da api para a interface que o representa
      this.listMovies = JSON.parse(JSON.stringify(values)).content as ListaInterface[];
    },
    (error)=>{
      console.log(error as JSON);

      this. showModalAlertError();
    });
  }
  // loadData()



  /**
   * @description responsavel por apresentar card informativo
   * @param values
   */
  private showModalAlert(values:any):void
  {
    // se a resposta do servidor não for 200 informo usuario da indisponibilidade da informação
    if(JSON.parse(JSON.stringify(values)).content.length > 0)
      document.getElementById("app-modalalert")!.style.display = "block";

    // se a reposta foi obtida com sucesso e o componente de informação esta aparente
    if(document.getElementById("app-modalalert")!.style.display == "block")
      document.getElementById("app-modalalert")!.style.display = "none";
  }
  //showModalAlert()

  private showModalAlertError():void
  {
    // apresenta mensagem para o usuário informando da indisponibilidade da informação
    document.getElementById("app-modalalert")!.style.display = "block";
  }




  /**
   * @description atualiza a lista a partir do ano informado pelo usuário
   *
   * @param event
   */
  updateByYear(event:any)
  {
    let year:string = (document.getElementById("filterByYear") as HTMLInputElement).value;

    if (this.isValidNumber(year))
    {
      this.listaService.listMoviesByFiltersYear(new Number(year).valueOf()).subscribe((values)=>{
        this.showModalAlert(values);

        console.log(values);


        this.listMovies = JSON.parse(JSON.stringify(values)).content as ListaInterface[];
      },
      (error)=>{
        console.log(error as JSON);

        this. showModalAlertError();
      }
      );
    }
  }// updateByYear()




  /**
   * @description valida o ano digitado pelo usuário
   *
   */
  private isValidNumber(year:any):boolean
  {
    return new Number(year).valueOf() != 0;
  }// isNumber()




  /**
   * @description atualiza com base se é vencedor ou não
   *
   */
  updateByWinner()
  {
    let winner = (document.getElementById("filterByWinner") as HTMLSelectElement).value;
    if(winner.length > 0 && winner != "")
    {
      this.listaService.listMoviesByFiltersWinner((document.getElementById("filterByWinner") as HTMLSelectElement).value)
      .subscribe((values)=>{
        this.showModalAlert(values);

        this.listMovies = JSON.parse(JSON.stringify(values)).content as ListaInterface[];
      },
      (error)=>{
        console.log(error as JSON);

        this. showModalAlertError();
      }
      );
    }
  }
  // updateByWinner()
}// class ListaComponent
