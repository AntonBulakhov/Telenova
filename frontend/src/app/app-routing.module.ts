import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./modules/pages/home/home.component";
import {ProfileComponent} from "./modules/pages/profile/profile.component";
import {RegistrationComponent} from "./modules/pages/registration/registration.component";
import {MobiletariffComponent} from "./modules/pages/mobiletariff/mobiletariff.component";
import {InternetComponent} from "./modules/pages/internet/internet.component";
import {TariffComponent} from "./modules/pages/tariff/tariff.component";
import {MobilesubmitComponent} from "./modules/pages/mobilesubmit/mobilesubmit.component";
import {InternetsubmitComponent} from "./modules/pages/internetsubmit/internetsubmit.component";
import {RequestsComponent} from "./modules/pages/requests/requests.component";
import {AdminmenuComponent} from "./modules/pages/adminmenu/adminmenu.component";
import {MobileconfigurationComponent} from "./modules/pages/mobileconfiguration/mobileconfiguration.component";
import {InternetconfigurationComponent} from "./modules/pages/internetconfiguration/internetconfiguration.component";
import {OfferingsconfigurationComponent} from "./modules/pages/offeringsconfiguration/offeringsconfiguration.component";
import {UsersComponent} from "./modules/pages/users/users.component";


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'mobiletariff', component: MobiletariffComponent},
  {path: 'internet', component: InternetComponent},
  {path: 'tariff', component: TariffComponent},
  {path: 'mobile/submit', component: MobilesubmitComponent},
  {path: 'internet/submit', component: InternetsubmitComponent},
  {path: 'requests', component: RequestsComponent},
  {path: 'adminmenu', component: AdminmenuComponent},
  {path: 'order/configuration/mobile', component: MobileconfigurationComponent},
  {path: 'order/configuration/internet', component: InternetconfigurationComponent},
  {path: 'offering/configuration', component: OfferingsconfigurationComponent},
  {path: 'users', component: UsersComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
