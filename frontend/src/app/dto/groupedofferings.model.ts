import {OfferingModel} from "../models/offering.model";

export class GroupedOfferingsModel {
  mobileInternet: OfferingModel[];
  mobileMinutesIn: OfferingModel[];
  mobileMinutesOut: OfferingModel[];
  internetSpeed: OfferingModel[];
  internetEquipment: OfferingModel[];
  internetSoft: OfferingModel[];
}
