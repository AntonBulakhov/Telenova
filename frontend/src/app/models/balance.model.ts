export class BalanceModel {
  id: string;
  value: string;

  static cloneBase(role: BalanceModel): BalanceModel {
    const cloneRoleModel: BalanceModel = new BalanceModel();
    cloneRoleModel.id = role.id;
    cloneRoleModel.value = role.value;
    return cloneRoleModel;
  }
}
