import {Component, OnInit} from '@angular/core';
import {OfferService} from "../../../services/offer.service";
import {OfferDTOModel} from "../../../dto/offerdto.model";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public mobileOffers: OfferDTOModel[];

  constructor(private offerService: OfferService) { }

  ngOnInit() {
    this.offerService.getMainMobileOffers().subscribe(value => {
      this.mobileOffers = value as OfferDTOModel[];
    });
  }

}
