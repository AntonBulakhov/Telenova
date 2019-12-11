export class UserStatusModel {
  id:string;
  name: string;

  static cloneBase(model: UserStatusModel):UserStatusModel {
    const cloneModel: UserStatusModel = new UserStatusModel();
    cloneModel.id = model.id;
    cloneModel.name = model.name;
    return cloneModel;
  }
}
