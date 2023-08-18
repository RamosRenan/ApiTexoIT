import { ComponentFixture, TestBed, fakeAsync, tick  } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ListaComponent } from './lista.component';
import { ListaService } from '../service/lista/lista.service';
import { HttpClient, HttpClientModule, HttpHeaders, HttpResponse } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';
import { MatCard, MatCardModule } from '@angular/material/card';
import { ModalalertComponent } from '../general/modalalert/modalalert.component';
import { of } from 'rxjs';

describe('ListaComponent', () => {
  let component: ListaComponent;
  let fixture: ComponentFixture<ListaComponent>;
  let listaService:ListaService;
  let httpTestController:HttpTestingController;
  let httpClient:HttpClient;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListaComponent, ModalalertComponent],
      providers:[ListaService, HttpClient, MatCard],
      imports:[HttpClientModule, HttpClientTestingModule, NgxPaginationModule, MatCardModule]
    });
    fixture = TestBed.createComponent(ListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    httpTestController = TestBed.inject(HttpTestingController);
    httpClient = TestBed.inject(HttpClient);
    listaService = TestBed.inject(ListaService);
  });

  it('do nothing', ()=>{


  });//it

  afterEach(() => {

  });
});
