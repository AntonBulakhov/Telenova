import { Component, OnInit } from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {Router} from "@angular/router";
import {UserModel} from "../../../models/user.model";
import {UserService} from "../../../services/user.service";
import {AuthService} from "../../../services/security/auth.service";
import {Constants} from "../../../constants/constants";
import {InternetServiceOfferModel} from "../../../dto/iserviceoffer.model";
import {ServService} from "../../../services/serv.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public loginUser: UserModel = new UserModel();

  private services: InternetServiceOfferModel[] = [];

  constructor(private storageService: StorageService,
              private router: Router,
              private userService: UserService,
              private auth: AuthService,
              private servService: ServService,) { }

  ngOnInit() {
    if(this.auth.user != null) {
      if (this.auth.user.role.id == Constants.EMPLOYEE_ROLE_ID || this.auth.user.role.id == Constants.CLIENT_ROLE_ID) {
        if (this.auth.user.role.id == Constants.EMPLOYEE_ROLE_ID) {
          this.servService.getInternetServicesByStatus(Constants.NEW_SERVICE_STATUS_ID).subscribe(value => {
            this.services = value as InternetServiceOfferModel[];
          });
        } else {
          this.servService.getInternetServicesByStatus(Constants.CONFIRMED_SERVICE_STATUS_ID).subscribe(value => {
            this.services = value as InternetServiceOfferModel[];
          });
        }
      }
    }
  }

  public onSubmit(): void {
    this.auth.signIn(this.loginUser);
    this.ngOnInit()
  }

  public logOut(): void {
    this.auth.logOut();
    this.ngOnInit();
  }

  registerUser(idRole): void {
    this.storageService.saveNewUserRoleId(idRole);
    this.router.navigate(['/registration'])
  }
}
