import {PhoneNumberModel} from "../models/phonenumber.model";
import {ServiceModel} from "../models/service.model";

export class NewServiceModel {
  service: ServiceModel = new ServiceModel();
  phoneNumber: PhoneNumberModel = new PhoneNumberModel();
}
