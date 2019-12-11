import {RoleModel} from "./role.model";
import {UserStatusModel} from "./userstatus.model";

export class UserModel {
  id: string;
  login: string;
  password: string;
  email: string;
  name: string;
  surname: string;
  role: RoleModel;
  userStatus: UserStatusModel;

  static cloneBase(model: UserModel): UserModel {
    const cloneModel: UserModel = new UserModel();
    cloneModel.id = model.id;
    cloneModel.login = model.login;
    cloneModel.password = model.password;
    cloneModel.email = model.email;
    cloneModel.name = model.name;
    cloneModel.surname = model.surname;
    cloneModel.role = model.role;
    cloneModel.userStatus = model.userStatus;
    return cloneModel;
  }
}

export class AuthenticationToken {
  token: string;
}
