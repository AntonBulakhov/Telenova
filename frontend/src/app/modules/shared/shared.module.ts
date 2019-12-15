import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { CardComponent } from './card/card.component';
import {RouterModule} from "@angular/router";
import { SocialComponent } from './social/social.component';
import {FormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    NavbarComponent,
    FooterComponent,
    CardComponent,
    SocialComponent
  ],
    imports: [
        CommonModule,
        RouterModule,
        FormsModule
    ],
  exports: [
    NavbarComponent,
    FooterComponent,
    CardComponent,
    SocialComponent
  ]
})
export class SharedModule { }
