import { Component, OnInit } from '@angular/core';
import {OfferService} from "../../../services/offer.service";
import {OfferDTOModel} from "../../../dto/offerdto.model";

@Component({
  selector: 'app-mobiletariff',
  templateUrl: './mobiletariff.component.html',
  styleUrls: ['./mobiletariff.component.css']
})
export class MobiletariffComponent implements OnInit {
  public page: number = 0;
  public pages: Array<number>;
  public mobileOffers: OfferDTOModel[];

  constructor(private offerService: OfferService) { }

  ngOnInit() {
    this.loadMobileOffers();
  }

  loadMobileOffers(): void {
    this.offerService.getMobilePage(this.page).subscribe(value => {
      this.mobileOffers = value['content'];
      this.pages = new Array<number>(value['totalPages']);
    });
  }

  public setPage(i, event: any){
    event.preventDefault();
    if (i < 0) {
      this.page = this.pages.length-1;
    } else if(i > this.pages.length-1){
      this.page = 0;
    }else {
      this.page = i;
    }
    this.loadMobileOffers();
  }
}
