import { Component } from '@angular/core';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { DashboardService } from '../service/dashborad/dashboard.service';
import { MultipleWinnersInterface } from '../interfaces/dashboard/multiple-winners.interface';
import { StudiosWithWinnersInterface } from '../interfaces/dashboard/studios-with-winners.interface';
import { MinAndMaxWinnersInterface } from '../interfaces/dashboard/min-and-max-winners.interface';
import { WinnersByYearInterface } from '../interfaces/dashboard/winners-by-year.interface';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent
{
  constructor(private dashboardService:DashboardService){}

  public listYearsWithMultipleWinners!:MultipleWinnersInterface[];

  public top3StudiosWithWinners!:StudiosWithWinnersInterface[];

  public maxWinner!:MinAndMaxWinnersInterface;

  public minWinner!:MinAndMaxWinnersInterface;

  public listWinnersByYear!:WinnersByYearInterface[];

  public searchByYear!:number;

  ngOnInit()
  {
    this.dashboardService.listYearsWithMultipleWinners().subscribe(value=>{
      this.listYearsWithMultipleWinners = JSON.parse(JSON.stringify(value)).years as MultipleWinnersInterface[];
    });

    this.dashboardService.listTop3StudiosWithWinners().pipe().subscribe(value=>{
      this.top3StudiosWithWinners = JSON.parse(JSON.stringify(value)).studios as StudiosWithWinnersInterface[];

      this.sortedDescStudio(this.top3StudiosWithWinners);

      this.top3StudiosWithWinners = this.top3StudiosWithWinners.slice(0,3);
    });

    this.dashboardService.listMinAndMaxWinners().subscribe((value)=>{
      this.minWinner = JSON.parse(JSON.stringify(value)).min[0];

      this.maxWinner = JSON.parse(JSON.stringify(value)).max[0];
    });
  }// ngOnInit

  private sortedDescStudio(studiosWithWinnersInterface:StudiosWithWinnersInterface[]):StudiosWithWinnersInterface[]
  {
    return studiosWithWinnersInterface.sort((a, b):number=>{
      if(a.winCount > b.winCount)
        return 0;
      else if(a.winCount < b.winCount)
        return -1;
      else
        return 0;
    });
  }

  listMovieWinnerByYear()
  {
    this.dashboardService.listMovieWinnerByYear(this.searchByYear).subscribe(value=>{
      this.listWinnersByYear = JSON.parse(JSON.stringify(value)) as WinnersByYearInterface[];
    });
  }

}// DashboardComponent
