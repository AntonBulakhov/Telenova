import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {Router} from "@angular/router";
import {AuthService} from "../../../services/security/auth.service";
import {UserService} from "../../../services/user.service";
import {UserModel} from "../../../models/user.model";
import {RoleModel} from "../../../models/role.model";
import {UserStatusModel} from "../../../models/userstatus.model";
import {Constants} from "../../../constants/constants";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  public roleId: string;
  public newUser: UserModel = new UserModel();
  public passwordConf: string;

  private roles: RoleModel[] = [];
  private statuses: UserStatusModel[] = [];

  constructor(private storageService: StorageService,
              private router: Router,
              private auth: AuthService,
              private userService: UserService) {
  }

  ngOnInit() {
    this.roleId = this.storageService.getNewUserRoleId();
    this.userService.getAllUserRoles().subscribe(value => {
      this.roles = value as RoleModel[]
    });
    this.userService.getAllUserStatuses().subscribe(value => {
      this.statuses = value as UserStatusModel[];
    });
  }

  onRegistration(): void {
    this.newUser.role = this.getUserRole();
    this.newUser.userStatus = this.getActiveStatus();

    if (this.roleId == Constants.CLIENT_ROLE_ID) {
      this.auth.signUp(this.newUser);
    } else {
      this.userService.saveUser(this.newUser).subscribe(value => {
        if (value) {
          this.router.navigate(['/']);
        }
      });
    }
  }

  getActiveStatus(): UserStatusModel {
    return this.statuses.find(obj => obj.id == Constants.ACTIVE_USER_STATUS_ID);
  }

  getUserRole(): RoleModel {
    return this.roles.find(obj => obj.id == this.roleId);
  }
}
