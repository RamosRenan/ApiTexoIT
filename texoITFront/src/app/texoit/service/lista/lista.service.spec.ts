import { TestBed } from '@angular/core/testing';

import { ListaService } from './lista.service';
import { HttpClient, HttpClientModule, HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Observable, of } from 'rxjs';

describe('ListaService', () => {
  let service: ListaService;
  let httpController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers:[HttpClient],
      imports:[HttpClientTestingModule]
    });
    service         = TestBed.inject(ListaService);
    httpController  = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpController.verify();
  });

  it('<<Test TEXO API>> should be created', () => {
    expect(service).toBeTruthy();
  });

  /**
   * @description responsavel por validar o recebimento da lista de filmes default
   */
  it( '<<Test TEXO API>> Testa listagem de filmes', () => {
    // 1 passo denifir o modelo de retorno
    const expectReturnResponseModel = [
        {
          "id": 200,
          "year": 2018,
          "title": "Robin Hood",
          "studios": [
              "Summit Entertainment"
          ],
          "producers": [
              "Jennifer Davisson",
              "Leonardo DiCaprio"
          ],
          "winner": false
      },

      {
        "id": 201,
        "year": 2018,
        "title": "Winchester",
        "studios": [
            "Lionsgate"
        ],
        "producers": [
            "Brett Tomberlin",
            "Tim McGahan"
        ],
        "winner": false
      }
    ];

    // increve-se para obter respota do servidor
    service.listAndPeageMovies().subscribe((res) => {
      console.log(res);

      expect(res).toEqual(expectReturnResponseModel);
    });

    const req = httpController.expectOne({
      method: 'GET',
      url: "https://tools.texoit.com/backend-java/api/movies?page=1&size=2&winner=false&year=2018",
    });

    // executa a requisição para ser interceptada por HttpTestingController
    req.flush(expectReturnResponseModel);
  });

  /**
   * @description testa consulta de filmes por ano
   */
  it('<<Test TEXO API>> Testa consulta de filmes por ANO', ()=>{
    let year  = 2017;

    const expectReturnResponseModel = [
        {
          "id": 195,
          "year": 2017,
          "title": "The Mummy",
          "studios": [
              "Universal Pictures"
          ],
          "producers": [
              "Alex Kurtzman",
              "Chris Morgan",
              "Sarah Bradshaw",
              "Sean Daniel"
          ],
          "winner": false
        },

      {
        "id": 196,
        "year": 2017,
        "title": "Transformers: The Last Knight",
        "studios": [
            "Paramount Pictures"
        ],
        "producers": [
            "Don Murphy",
            "Ian Bryce",
            "Lorenzo di Bonaventura",
            "Tom DeSanto"
        ],
        "winner": false
      }
    ];

    service.listMoviesByFiltersYear(year).subscribe((value)=>{
      expect(value).toEqual(expectReturnResponseModel);
    });

    let httpResp = httpController.expectOne({
      method: 'GET',
      url: "https://tools.texoit.com/backend-java/api/movies?page=1&size=2&winner=false&year="+year
    });

    expect(httpResp.request.method).toBe('GET');

    httpResp.flush(expectReturnResponseModel);
  });
  // it

  /**
   * @description testa consulta de filmes por vencedor
   */
  it('<<Test TEXO API>> Testa consulta de filmes por VENCEDOR', ()=>{
    let winner  = "false";

    const expectReturnResponseModel = [
        {
          "id": 195,
          "year": 2018,
          "title": "The Mummy",
          "studios": [
              "Universal Pictures"
          ],
          "producers": [
              "Alex Kurtzman",
              "Chris Morgan",
              "Sarah Bradshaw",
              "Sean Daniel"
          ],
          "winner": false
        },

      {
        "id": 196,
        "year": 2018,
        "title": "Transformers: The Last Knight",
        "studios": [
            "Paramount Pictures"
        ],
        "producers": [
            "Don Murphy",
            "Ian Bryce",
            "Lorenzo di Bonaventura",
            "Tom DeSanto"
        ],
        "winner": false
      }
    ];

    service.listMoviesByFiltersWinner(winner).subscribe((value)=>{
      expect(value).toEqual(expectReturnResponseModel);
    });

    let httpResp = httpController.expectOne({
      method: 'GET',
      url: "https://tools.texoit.com/backend-java/api/movies?page=1&size=2&winner="+winner+"&year=2018"
    });

    expect(httpResp.request.method).toBe('GET');

    httpResp.flush(expectReturnResponseModel);
  });
  // it
});
