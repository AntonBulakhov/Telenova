import { Component, OnInit } from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {Router} from "@angular/router";
import {UserModel} from "../../../models/user.model";
import {UserService} from "../../../services/user.service";
import {AuthService} from "../../../services/security/auth.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public loginUser: UserModel = new UserModel();

  constructor(private storageService: StorageService,
              private router: Router,
              private userService: UserService,
              private auth: AuthService) { }

  ngOnInit() {
  }

  public onSubmit(): void {
    this.auth.signIn(this.loginUser);
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
