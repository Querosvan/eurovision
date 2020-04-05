import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CitylistComponent } from './pages/citylist/citylist.component';
import { HomeComponent } from './pages/home/home.component';


const routes: Routes = [
  { path: 'cities', component: CitylistComponent },
  { path: '', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
