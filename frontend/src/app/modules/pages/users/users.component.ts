import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public roleId: string;

  constructor(private storageService: StorageService,
              private router: Router) {
  }

  ngOnInit() {
    this.roleId = this.storageService.getRoleId();
  }

  registerUser(idRole: string): void {
    this.storageService.saveNewUserRoleId(idRole);
    this.router.navigate(['/registration'])
  }
}
