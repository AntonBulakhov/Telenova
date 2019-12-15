import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {OfferService} from "../../../services/offer.service";
import {OfferDTOModel} from "../../../dto/offerdto.model";
import {Router} from "@angular/router";
import {AuthService} from "../../../services/security/auth.service";

@Component({
  selector: 'app-tariff',
  templateUrl: './tariff.component.html',
  styleUrls: ['./tariff.component.css']
})
export class TariffComponent implements OnInit {

  public offer: OfferDTOModel;

  constructor(private storageService: StorageService,
              private offerService: OfferService,
              private router: Router,
              private auth: AuthService) { }

  ngOnInit() {
    let id: string = this.storageService.getOfferId();
    this.offerService.getMobileOfferById(id).subscribe(value => {
      this.offer = value as OfferDTOModel;
    })
  }

  onConnectClicked() {
    this.storageService.saveOfferId(this.offer.offer.id);
    this.router.navigate(['/mobile/submit']);
  }
}
