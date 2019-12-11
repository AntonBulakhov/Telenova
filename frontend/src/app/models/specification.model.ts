export class SpecificationModel {
  id: string;
  name: string;

  static cloneBase(model: SpecificationModel): SpecificationModel {
    const cloneModel: SpecificationModel = new SpecificationModel();
    cloneModel.id = model.id;
    cloneModel.name = model.name;
    return cloneModel;
  }
}
