import { Component, OnInit } from '@angular/core';
import {ProfileService} from "../../../services/profile.service";
import {ServService} from "../../../services/serv.service";
import {ProfileMobileOfferModel} from "../../../dto/profilemobileoffer.model";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public mobileServices: ProfileMobileOfferModel[];

  constructor(private profileService: ProfileService,
              private servService: ServService) { }

  ngOnInit() {
    this.servService.getMobileServiceByUserId('1').subscribe(value => {
      this.mobileServices = value as ProfileMobileOfferModel[];
    });
  }

}
