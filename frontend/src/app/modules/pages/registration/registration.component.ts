import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  public roleId: string;

  constructor(private storageService: StorageService,
              private router: Router) {
  }

  ngOnInit() {
    this.roleId = this.storageService.getNewUserRoleId();
  }

}
