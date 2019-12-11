import { Component, OnInit } from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private storageService: StorageService,
              private router: Router) { }

  ngOnInit() {
  }

  registerUser(idRole): void {
    this.storageService.saveNewUserRoleId(idRole);
    this.router.navigate(['/registration'])
  }
}
