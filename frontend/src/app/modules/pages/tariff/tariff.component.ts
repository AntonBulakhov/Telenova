import { Component, OnInit } from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {Router} from "@angular/router";
import {OfferService} from "../../../services/offer.service";
import {OfferDTOModel} from "../../../dto/offerdto.model";

@Component({
  selector: 'app-tariff',
  templateUrl: './tariff.component.html',
  styleUrls: ['./tariff.component.css']
})
export class TariffComponent implements OnInit {

  public offer: OfferDTOModel;

  constructor(private storageService: StorageService,
              private offerService: OfferService) { }

  ngOnInit() {
    let id: string = this.storageService.getOfferId();
    this.offerService.getMobileOfferById(id).subscribe(value => {
      this.offer = value as OfferDTOModel;
    })
  }

  onConnectClicked() {

  }
}
