export class OfferingTypeModel {
  id: string;
  name: string;

  static cloneBase(role: OfferingTypeModel): OfferingTypeModel {
    const cloneRoleModel: OfferingTypeModel = new OfferingTypeModel();
    cloneRoleModel.id = role.id;
    cloneRoleModel.name = role.name;
    return cloneRoleModel;
  }
}
