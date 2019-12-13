import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {PagesModule} from "./modules/pages/pages.module";
import {HttpClientModule} from "@angular/common/http";
import {StorageService} from "./services/storage/storage.service";
import {OfferingService} from "./services/offering.service";
import {SpecificationService} from "./services/specification.service";
import {PhoneNumberService} from "./services/phonenumber.service";
import {ProfileService} from "./services/profile.service";
import {ServService} from "./services/serv.service";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PagesModule,
    HttpClientModule
  ],
  providers: [
    StorageService,
    OfferingService,
    SpecificationService,
    PhoneNumberService,
    ProfileService,
    ServService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
