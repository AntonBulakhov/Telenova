import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";
import {AuthService} from "../../../services/security/auth.service";
import {UserModel} from "../../../models/user.model";

@Component({
  selector: 'app-adminmenu',
  templateUrl: './adminmenu.component.html',
  styleUrls: ['./adminmenu.component.css']
})
export class AdminmenuComponent implements OnInit {

  public currentUser: UserModel = new UserModel();

  constructor(private router: Router,
              private storageService: StorageService,
              private auth: AuthService) {
  }

  ngOnInit() {
    this.auth.userAuth().subscribe(value => {
      this.currentUser = value as UserModel;
      if(this.currentUser.role.id == '4') {
        this.router.navigate(['/']);
      }
    });
  }

  navigateToUsers(roleID): void {
    this.storageService.saveRoleId(roleID);
    this.router.navigate(['/users']);
  }
}
