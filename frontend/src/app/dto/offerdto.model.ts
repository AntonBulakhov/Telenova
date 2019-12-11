import {OfferModel} from "../models/offer.model";
import {OfferingModel} from "../models/offering.model";

export class OfferDTOModel {
  offer: OfferModel = new OfferModel();
  mobileInternet: OfferingModel = new OfferingModel();
  mobileMinutesIn: OfferingModel = new OfferingModel();
  mobileMinutesOut: OfferingModel = new OfferingModel();
}
