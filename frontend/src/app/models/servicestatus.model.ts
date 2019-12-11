export class ServiceStatusModel {
  id: string;
  name: string;

  static cloneBase(model: ServiceStatusModel): ServiceStatusModel {
    const cloneModel: ServiceStatusModel = new ServiceStatusModel();
    cloneModel.id = model.id;
    cloneModel.name = model.name;
    return cloneModel;
  }
}
