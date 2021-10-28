import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AnnualStatementComponent } from './annual-statement/annual-statement.component';

const routes: Routes = [
  { path: 'create', component: AnnualStatementComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
