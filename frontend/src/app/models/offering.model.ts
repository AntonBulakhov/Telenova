import {SpecificationModel} from "./specification.model";
import {ValueMeasureModel} from "./valuemeasure.model";
import {OfferingTypeModel} from "./offeringtype.model";

export class OfferingModel {
  id: string;
  value: string;
  specification: SpecificationModel;
  offeringType: OfferingTypeModel;
  valueMeasure: ValueMeasureModel;

  static cloneBase(model: OfferingModel): OfferingModel {
    const cloneModel: OfferingModel = new OfferingModel();
    cloneModel.id = model.id;
    cloneModel.value = model.value;
    cloneModel.specification = model.specification;
    cloneModel.offeringType = model.offeringType;
    cloneModel.valueMeasure = model.valueMeasure;
    return cloneModel;
  }
}
