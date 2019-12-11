export class AddressModel {
  id: string;
  city: string;
  street: string;
  house: string;
  flat: string;

  static cloneBase(role: AddressModel): AddressModel {
    const cloneRoleModel: AddressModel = new AddressModel();
    cloneRoleModel.id = role.id;
    cloneRoleModel.city = role.city;
    cloneRoleModel.street = role.street;
    cloneRoleModel.house = role.house;
    cloneRoleModel.flat = role.flat;
    return cloneRoleModel;
  }
}
