import {Component, OnInit} from '@angular/core';
import {ProfileService} from "../../../services/profile.service";
import {ServService} from "../../../services/serv.service";
import {ProfileMobileOfferModel} from "../../../dto/profilemobileoffer.model";
import {BalanceModel} from "../../../models/balance.model";
import {InternetServiceOfferModel} from "../../../dto/iserviceoffer.model";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public mobileTabSelected: boolean = true;

  public mobileServices: ProfileMobileOfferModel[];
  public internetServices: InternetServiceOfferModel[];

  public balanceToFill: BalanceModel;

  public sum: string;

  constructor(private profileService: ProfileService,
              private servService: ServService) {
  }

  ngOnInit() {
    this.servService.getMobileServiceByUserId('1').subscribe(value => {
      this.mobileServices = value as ProfileMobileOfferModel[];
    });
    this.servService.getInternetServicesByUserId('1').subscribe(value => {
      this.internetServices = value as InternetServiceOfferModel[];
    })
  }

  onBalanceClick(balanceModel: BalanceModel): void {
    this.balanceToFill = balanceModel;
  }

  onPaid(): void {
    this.balanceToFill.value = this.sum;
    this.servService.fillBalance(this.balanceToFill).subscribe(value => {
      this.ngOnInit();
    })
  }

  setTabSelected(tab: boolean): void {
    this.mobileTabSelected = tab;
  }
}
