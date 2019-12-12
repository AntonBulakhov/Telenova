import { Component, OnInit } from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {OfferDTOModel} from "../../../dto/offerdto.model";
import {OfferService} from "../../../services/offer.service";
import {PhoneNumberModel} from "../../../models/phonenumber.model";
import {PhoneNumberService} from "../../../services/phonenumber.service";
import {ServiceStatusModel} from "../../../models/servicestatus.model";
import {ServService} from "../../../services/serv.service";
import {NewServiceModel} from "../../../dto/newservice.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-mobilesubmit',
  templateUrl: './mobilesubmit.component.html',
  styleUrls: ['./mobilesubmit.component.css']
})
export class MobilesubmitComponent implements OnInit {

  public offer: OfferDTOModel;
  public connectPrice: number = 5;

  public serviceStatuses: ServiceStatusModel[];

  public phoneNumber: PhoneNumberModel = new PhoneNumberModel();

  constructor(private storageService: StorageService,
              private offerService: OfferService,
              private phoneNumberService: PhoneNumberService,
              private servService: ServService,
              private router: Router) { }

  ngOnInit() {
    let id: string = this.storageService.getOfferId();
    this.offerService.getMobileOfferById(id).subscribe(value => {
      this.offer = value as OfferDTOModel;
    });
    this.phoneNumberService.generateNumber().subscribe(value => {
      this.phoneNumber = value;
    });
    this.servService.getAllServiceStatuses().subscribe(value => {
      this.serviceStatuses = value as ServiceStatusModel[];
    });
  }

  onPaidMobileTariff(): void {
    let newService: NewServiceModel = new NewServiceModel();
    newService.phoneNumber = this.phoneNumber;
    newService.service.offerId = this.offer.offer.id;
    newService.service.serviceStatus = this.getPaidStatus('3');
    this.servService.createMobileService(newService).subscribe(value => {
      this.router.navigate(['/profile']);
    });
  }

  public getPaidStatus(id: string): ServiceStatusModel {
    return this.serviceStatuses.find(obj => obj.id == id);
  }

}
