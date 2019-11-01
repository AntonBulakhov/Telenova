import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import {SharedModule} from "../shared/shared.module";
import {BrowserModule} from "@angular/platform-browser";
import {RouterModule} from "@angular/router";
import { ProfileComponent } from './profile/profile.component';
import { RegistrationComponent } from './registration/registration.component';



@NgModule({
  declarations: [HomeComponent, ProfileComponent, RegistrationComponent],
  imports: [
    CommonModule,
    SharedModule,
    BrowserModule,
    RouterModule,
  ],
  exports: [
    HomeComponent,
    ProfileComponent,
    RegistrationComponent
  ]
})
export class PagesModule { }
