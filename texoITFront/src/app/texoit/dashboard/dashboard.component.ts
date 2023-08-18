import { Component } from '@angular/core';
import { DashboardService } from '../service/dashboard/dashboard.service';
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
    this.listYearsWithMultipleWinnersFn();

    this.listTop3StudiosWithWinners();

    this.listMinAndMaxWinners();
  }// ngOnInit

  /**
   *
   * @description List years with multiple winners
   */
  listYearsWithMultipleWinnersFn():void
  {
    this.dashboardService.listYearsWithMultipleWinners().subscribe(value=>{
      console.log(value);

      this.listYearsWithMultipleWinners = JSON.parse(JSON.stringify(value))
      .years as MultipleWinnersInterface[];
    });
  }

  /**
   * @description listTop3StudiosWithWinners
   */
  listTop3StudiosWithWinners()
  {
    this.dashboardService.listTop3StudiosWithWinners().pipe().subscribe(value=>{
      console.log(value);

      this.top3StudiosWithWinners = JSON.parse(JSON.stringify(value))
      .studios as StudiosWithWinnersInterface[];

      // ja vem sorteado em desc
      // this.sortedDescStudio(this.top3StudiosWithWinners);

      console.log(this.top3StudiosWithWinners);

      this.top3StudiosWithWinners = this.top3StudiosWithWinners.slice(0,3);
    });
  }

  /**
   * @description  Producers with longest interval between wins
  */
  listMinAndMaxWinners()
  {
    this.dashboardService.listMinAndMaxWinners().subscribe((value)=>{
      console.log(value);


      this.minWinner = JSON.parse(JSON.stringify(value)).min[0];

      this.maxWinner = JSON.parse(JSON.stringify(value)).max[0];
    });
  }

  /**
   * @description Ordena os estudios em ordem decrescente
   * @param studiosWithWinnersInterface
   * @returns
   */
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

  /**
   * @description List movie winners by year
   */
  listMovieWinnerByYear()
  {
    this.dashboardService.listMovieWinnerByYear(this.searchByYear).subscribe(value=>{
      console.log(value);

      this.listWinnersByYear = JSON.parse(JSON.stringify(value)) as WinnersByYearInterface[];
    });
  }

}// DashboardComponent
