export class RoleModel {
  id: string;
  name: string;

  static cloneBase(model: RoleModel): RoleModel {
    const cloneModel: RoleModel = new RoleModel();
    cloneModel.id = model.id;
    cloneModel.name = model.name;
    return cloneModel;
  }
}
