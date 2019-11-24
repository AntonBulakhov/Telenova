import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./modules/pages/home/home.component";
import {ProfileComponent} from "./modules/pages/profile/profile.component";
import {RegistrationComponent} from "./modules/pages/registration/registration.component";
import {MobiletariffComponent} from "./modules/pages/mobiletariff/mobiletariff.component";
import {InternetComponent} from "./modules/pages/internet/internet.component";
import {TariffComponent} from "./modules/pages/tariff/tariff.component";


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'mobiletariff', component: MobiletariffComponent},
  {path: 'internet', component: InternetComponent},
  {path: 'tariff', component: TariffComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
