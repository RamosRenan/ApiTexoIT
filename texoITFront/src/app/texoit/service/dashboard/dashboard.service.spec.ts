import { TestBed } from '@angular/core/testing';

import { DashboardService } from './dashboard.service';
import { HttpClient } from '@angular/common/http';
import { FormsModule, NgModel, ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('DashboardService', () => {
  let service: DashboardService;
  let httpTestingController:HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers:[HttpClient, NgModel],
      imports:[HttpClientTestingModule, FormsModule, ReactiveFormsModule ]
    });
    service               = TestBed.inject(DashboardService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(()=>{
    httpTestingController.verify();
  });

  /**
   * @description certifica-se que foi criado
   */
  it('<<Test TEXO API>> shold be created', () => {
    expect(service).toBeTruthy();
  });

  /**
   * @description List years with multiple winners
   */
  it('<<Test TEXO API>> List years with multiple winners', ()=>{
    const expectReturnResponseModel = [
      {
        "year": 1986,
        "winnerCount": 2
      },

      {
        "year": 1990,
        "winnerCount": 2
      },

      {
        "year": 2015,
        "winnerCount": 2
      }
    ];

    service.listYearsWithMultipleWinners().subscribe((value)=>{
      expect(value).toEqual(expectReturnResponseModel);
    });

    let httpReq = httpTestingController.expectOne({
      method: 'GET',
      url: "https://tools.texoit.com/backend-java/api/movies?projection=years-with-multiple-winners"
    });

    expect(httpReq.request.method).toBe('GET');

    httpReq.flush(expectReturnResponseModel);
  });
  //it

  /**
   * @description Top 3 studios with winners
   */
  it('<<Test TEXO API>> Top 3 studios with winners', ()=>{
    const expectReturnResponseModel = [
      {
        "name": "Columbia Pictures",
        "winCount": 7
      },

      {
        "name": "Paramount Pictures",
        "winCount": 6
      },

      {
        "name": "Warner Bros.",
        "winCount": 5
      }
    ];

    service.listTop3StudiosWithWinners().subscribe((value)=>{
      expect(value).toEqual(expectReturnResponseModel);
    });

    let httpReq = httpTestingController.expectOne({
      method: 'GET',
      url: "https://tools.texoit.com/backend-java/api/movies?projection=studios-with-win-count"
    });

    expect(httpReq.request.method).toBe('GET');

    httpReq.flush(expectReturnResponseModel);
  });
  // it

  /**
   * @description Producers with longest interval between wins
   */
  it('<<Test TEXO API>> Producers with longest interval between wins', ()=>{
    const expectReturnResponseModel = {
      max:[
            {
                "producer": "Matthew Vaughn",
                "interval": 13,
                "previousWin": 2002,
                "followingWin": 2015
            }
          ],
      min:[
            {
                "producer": "Joel Silver",
                "interval": 1,
                "previousWin": 1990,
                "followingWin": 1991
            }
          ]
    };

    service.listMinAndMaxWinners().subscribe((value)=>{
      expect(value).toEqual(expectReturnResponseModel);
    });

    let httpReq =  httpTestingController.expectOne({
      method: 'GET',
      url: "https://tools.texoit.com/backend-java/api/movies?projection=max-min-win-interval-for-producers"
    });

    expect(httpReq.request.method).toBe('GET');

    httpReq.flush(expectReturnResponseModel);
  });
  // it

  /**
   * @description List movie winners by year
   */
  it('<<Test TEXO API>> List movie winners by year', ()=>{
    let year = 2017;

    const expectReturnResponseModel = [
      {
        "id": 192,
        "year": 2017,
        "title": "The Emoji Movie",
        "studios": [
            "Columbia Pictures"
        ],
        "producers": [
            "Michelle Raimo Kouyate"
        ],
        "winner": true
      }
    ];

    service.listMovieWinnerByYear(year).subscribe((value)=>{
      expect(value).toEqual(expectReturnResponseModel);
    });

    let httpReq = httpTestingController.expectOne({
      method: 'GET',
      url: "https://tools.texoit.com/backend-java/api/movies?winner=true&year="+year
    });

    expect(httpReq.request.method).toBe('GET');

    httpReq.flush(expectReturnResponseModel);
  });
});
