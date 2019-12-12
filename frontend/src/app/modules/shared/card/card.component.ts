import {Component, Input, OnInit} from '@angular/core';
import {OfferDTOModel} from "../../../dto/offerdto.model";
import {StorageService} from "../../../services/storage/storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @Input() mobileTariff: OfferDTOModel;

  constructor(private storageService: StorageService,
              private router: Router) { }

  ngOnInit() {
  }

  onTariffPage(): void {
    this.storageService.saveOfferId(this.mobileTariff.offer.id);
    this.router.navigate(['/tariff']);
  }
}
