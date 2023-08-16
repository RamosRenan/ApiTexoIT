import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { PaginationInstance } from 'ngx-pagination/public-api';
import { HttpClient } from '@angular/common/http';
import { ListaService } from '../service/lista/lista.service';
import { ListaInterface } from '../interfaces/lista/lista.interface';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent
{

  public filter:string = '';

  p:number = 1;

  itemsPerPage:number = 10;

  config:PaginationInstance = {itemsPerPage:2, currentPage:1};

  // id, title, year winner
  listMovies!:ListaInterface[];

  constructor(private listaService:ListaService){}

  ngOnInit()
  {
    this.listaService.listAndPeageMovies().subscribe((values=>{
      this.listMovies = JSON.parse(JSON.stringify(values as JSON)).content as ListaInterface[];
    }));
  }// ngOnInit

  updateByYear(event:any)
  {
    let year:string = (document.getElementById("filterByYear") as HTMLInputElement).value;

    if (this.isValidNumber(year))
    {
      this.listaService.listMoviesByFiltersYear(new Number(year).valueOf()).subscribe(values=>{
        this.listMovies = JSON.parse(JSON.stringify(values as JSON)).content as ListaInterface[];
      });
    }
  }// updateByYear

  private isValidNumber(year:any):boolean
  {
    return new Number(year).valueOf() != 0;
  }// isNumber

  updateByWinner()
  {
    let winner = (document.getElementById("filterByWinner") as HTMLSelectElement).value;
    if(winner.length > 0 && winner != "")
    {
      this.listaService.listMoviesByFiltersWinner((document.getElementById("filterByWinner") as HTMLSelectElement).value).subscribe(values=>{
        this.listMovies = JSON.parse(JSON.stringify(values as JSON)).content as ListaInterface[];
      });
    }
  }
}// class ListaComponent
