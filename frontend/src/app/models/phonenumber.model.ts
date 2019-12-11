export class PhoneNumberModel {
  id: string;
  serviceId: string;
  userId: string;
  honeNumber: string

  static cloneBase(role: PhoneNumberModel): PhoneNumberModel {
    const cloneRoleModel: PhoneNumberModel = new PhoneNumberModel();
    cloneRoleModel.id = role.id;
    cloneRoleModel.serviceId = role.serviceId;
    cloneRoleModel.userId = role.userId;
    cloneRoleModel.honeNumber = role.honeNumber;
    return cloneRoleModel;
  }
}
