import {OfferModel} from "./offer.model";
import {AddressModel} from "./address.model";
import {ServiceStatusModel} from "./servicestatus.model";
import {BalanceModel} from "./balance.model";

export class ServiceModel {
  id: string;
  userId: string;
  offer: OfferModel;
  balance: BalanceModel;
  address: AddressModel;
  serviceStatus: ServiceStatusModel;

  static cloneBase(model: ServiceModel): ServiceModel {
    const cloneModel: ServiceModel = new ServiceModel();
    cloneModel.id = model.id;
    cloneModel.userId = model.userId;
    cloneModel.offer = model.offer;
    cloneModel.balance = model.balance;
    cloneModel.address = model.address;
    cloneModel.serviceStatus = model.serviceStatus;
    return cloneModel;
  }
}
