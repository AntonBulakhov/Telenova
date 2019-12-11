import {SpecificationModel} from "./specification.model";
import {OfferStatusModel} from "./offerstatus.model";

export class OfferModel {
  id: string;
  name: string;
  price: string;
  description: string;
  specification: SpecificationModel;
  offerStatus: OfferStatusModel;

  static cloneBase(model: OfferModel): OfferModel {
    const cloneModel: OfferModel = new OfferModel();
    cloneModel.id = model.id;
    cloneModel.name = model.name;
    cloneModel.price = model.price;
    cloneModel.description = model.description;
    cloneModel.specification = model.specification;
    cloneModel.offerStatus = model.offerStatus;
    return cloneModel;
  }
}
