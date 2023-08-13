import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './texoit/dashboard/dashboard.component';
import { ListaComponent } from './texoit/lista/lista.component';

const routes: Routes = [
  {path: 'dashboard', component: DashboardComponent},
  {path: 'lista',     component:ListaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
