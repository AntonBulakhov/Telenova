import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {Router} from "@angular/router";
import {AuthService} from "../../../services/security/auth.service";
import {UserModel} from "../../../models/user.model";
import {UserService} from "../../../services/user.service";
import {UserWithSumModel} from "../../../dto/userwithsum.model";
import {UserStatusModel} from "../../../models/userstatus.model";
import {Constants} from "../../../constants/constants";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public users: UserWithSumModel[] = [];

  public roleId: string;

  private statuses: UserStatusModel[] = [];

  constructor(private storageService: StorageService,
              private auth: AuthService,
              private router: Router,
              private userService: UserService) {
  }

  ngOnInit() {
    if(this.auth.user.role.id == '4') {
      this.router.navigate(['/']);
    }
    this.roleId = this.storageService.getRoleId();
    this.userService.getUsersByRoleId(this.roleId).subscribe(value => {
      this.users = value as UserWithSumModel[];
    });
    this.userService.getAllUserStatuses().subscribe(value => {
      this.statuses = value as UserStatusModel[];
    });
  }

  registerUser(idRole: string): void {
    this.storageService.saveNewUserRoleId(idRole);
    this.router.navigate(['/registration'])
  }

  setStatus(statusId: string, user: UserModel): void {
    user.userStatus = this.getStatus(statusId);
    this.userService.setUserStatus(user).subscribe(value => {
      this.ngOnInit();
    });
  }

  deleteUser(id: string): void {
    this.userService.deleteUser(id).subscribe(value => {
      this.ngOnInit();
    });
  }

  getStatus(statusId: string): UserStatusModel {
    return this.statuses.find(obj => obj.id == statusId);
  }
}
