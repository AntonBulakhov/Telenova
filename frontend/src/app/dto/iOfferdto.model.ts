import {OfferModel} from "../models/offer.model";
import {OfferingModel} from "../models/offering.model";

export class InternetOfferDtoModel {
  offer: OfferModel = new OfferModel();
  internetSpeed: OfferingModel = new OfferingModel();
  internetEquipment1: OfferingModel = new OfferingModel();
  internetEquipment2: OfferingModel = new OfferingModel();
  internetSoft1: OfferingModel = new OfferingModel();
  internetSoft2: OfferingModel = new OfferingModel();
}
