import {ServiceModel} from "../models/service.model";
import {OfferModel} from "../models/offer.model";
import {PhoneNumberModel} from "../models/phonenumber.model";

export class ProfileMobileOfferModel {
  service: ServiceModel;
  offer: OfferModel;
  phoneNumber: PhoneNumberModel;
}
