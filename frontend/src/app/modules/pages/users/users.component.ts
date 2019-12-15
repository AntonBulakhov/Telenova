import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {Router} from "@angular/router";
import {AuthService} from "../../../services/security/auth.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public roleId: string;

  constructor(private storageService: StorageService,
              private auth: AuthService,
              private router: Router) {
  }

  ngOnInit() {
    if(this.auth.user.role.id == '4') {
      this.router.navigate(['/']);
    }
    this.roleId = this.storageService.getRoleId();
  }

  registerUser(idRole: string): void {
    this.storageService.saveNewUserRoleId(idRole);
    this.router.navigate(['/registration'])
  }
}
