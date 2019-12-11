export class OfferStatusModel {
  id: string;
  name: string;

  static cloneBase(model: OfferStatusModel): OfferStatusModel {
    const cloneModel: OfferStatusModel = new OfferStatusModel();
    cloneModel.id = model.id;
    cloneModel.name = model.name;
    return cloneModel;
  }
}
