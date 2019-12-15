import {Component, OnInit} from '@angular/core';
import {ProfileService} from "../../../services/profile.service";
import {ServService} from "../../../services/serv.service";
import {ProfileMobileOfferModel} from "../../../dto/profilemobileoffer.model";
import {BalanceModel} from "../../../models/balance.model";
import {InternetServiceOfferModel} from "../../../dto/iserviceoffer.model";
import {UserModel} from "../../../models/user.model";
import {AuthService} from "../../../services/security/auth.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public mobileTabSelected: boolean = true;

  public mobileServices: ProfileMobileOfferModel[] = [];
  public internetServices: InternetServiceOfferModel[] = [];

  public balanceToFill: BalanceModel;

  public sum: string;

  public user: UserModel;

  constructor(private profileService: ProfileService,
              private servService: ServService,
              private auth: AuthService) {
  }

ngOnInit() {
  this.auth.userAuth().subscribe(value => {
    this.user = value as UserModel;
  });
  this.servService.getMobileServiceByUserId(this.auth.user.id).subscribe(value => {
      this.mobileServices = value as ProfileMobileOfferModel[];
    });
    this.servService.getInternetServicesByUserId(this.auth.user.id).subscribe(value => {
      this.internetServices = value as InternetServiceOfferModel[];
    });

    if(this.mobileServices.length == 0) {
      this.mobileTabSelected = false;
    }
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
