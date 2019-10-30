import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import {SharedModule} from "../shared/shared.module";
import {BrowserModule} from "@angular/platform-browser";
import {RouterModule} from "@angular/router";
import { ProfileComponent } from './profile/profile.component';



@NgModule({
  declarations: [HomeComponent, ProfileComponent],
  imports: [
    CommonModule,
    SharedModule,
    BrowserModule,
    RouterModule,
  ],
  exports: [
    HomeComponent,
    ProfileComponent
  ]
})
export class PagesModule { }
