import { Component, OnInit } from '@angular/core';
import {OfferService} from "../../../services/offer.service";
import {InternetOfferDtoModel} from "../../../dto/iOfferdto.model";
import {StorageService} from "../../../services/storage/storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-internet',
  templateUrl: './internet.component.html',
  styleUrls: ['./internet.component.css']
})
export class InternetComponent implements OnInit {

  public offers: InternetOfferDtoModel[];

  constructor(private offerService: OfferService,
              private storageService: StorageService,
              private router: Router) { }

  ngOnInit() {
    this.offerService.getInternetOffers().subscribe(value => {
      this.offers = value as InternetOfferDtoModel[];
    });
  }

  onBuy(offerId: string): void {
    this.storageService.saveOfferId(offerId);
    this.router.navigate(['/internet/submit']);
  }
}
