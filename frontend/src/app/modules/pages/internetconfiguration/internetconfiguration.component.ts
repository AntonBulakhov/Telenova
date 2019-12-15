import {Component, OnInit} from '@angular/core';
import {InternetOfferDtoModel} from "../../../dto/iOfferdto.model";
import {OfferStatusModel} from "../../../models/offerstatus.model";
import {SpecificationModel} from "../../../models/specification.model";
import {GroupedOfferingsModel} from "../../../dto/groupedofferings.model";
import {OfferingService} from "../../../services/offering.service";
import {SpecificationService} from "../../../services/specification.service";
import {OfferService} from "../../../services/offer.service";
import {OfferModel} from "../../../models/offer.model";
import {AuthService} from "../../../services/security/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-internetconfiguration',
  templateUrl: './internetconfiguration.component.html',
  styleUrls: ['./internetconfiguration.component.css']
})
export class InternetconfigurationComponent implements OnInit {

  private offers: InternetOfferDtoModel[];

  private offerStatuses: OfferStatusModel[];
  private groupedOfferings: GroupedOfferingsModel;
  private specifications: SpecificationModel[];

  public newOffer: InternetOfferDtoModel = new InternetOfferDtoModel();

  constructor(private offeringService: OfferingService,
              private specificationService: SpecificationService,
              private offerService: OfferService,
              private auth: AuthService,
              private router: Router) { }

  ngOnInit() {
    if(this.auth.user.role.id == '4') {
      this.router.navigate(['/']);
    }
    this.offerService.getInternetOffers().subscribe(value => {
      this.offers = value as InternetOfferDtoModel[];
    });
    this.offeringService.getGroupedOfferingsBySpecId('2').subscribe(value => {
      this.groupedOfferings = value as GroupedOfferingsModel;
    });
    this.specificationService.getAllSpecifications().subscribe(value => {
      this.specifications = value as SpecificationModel[];
    });
    this.offerService.getAllOfferStatuses().subscribe(value => {
      this.offerStatuses = value as OfferStatusModel[];
    });
  }

  createOffer(): void {
    this.newOffer.offer.offerStatus = this.getOfferStatus('1');
    this.newOffer.offer.specification = this.getSpecification('2');
    this.offerService.createInternetOffer(this.newOffer).subscribe(value => {
      this.ngOnInit();
    })
  }

  public getSpecification(id: string): SpecificationModel {
    return this.specifications.find(obj => obj.id == id);
  }

  public getOfferStatus(id: string): OfferStatusModel {
    return this.offerStatuses.find(obj => obj.id == id);
  }

  setOfferStatus(offerId: string, statusId: string): void {
    let offerS: OfferModel = new OfferModel();
    offerS.id = offerId;
    offerS.offerStatus = this.getOfferStatus(statusId);
    this.offerService.setOfferStatus(offerS).subscribe(value => {
      this.ngOnInit();
    });
  }
}
