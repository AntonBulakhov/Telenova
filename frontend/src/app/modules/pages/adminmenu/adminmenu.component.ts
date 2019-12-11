import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";

@Component({
  selector: 'app-adminmenu',
  templateUrl: './adminmenu.component.html',
  styleUrls: ['./adminmenu.component.css']
})
export class AdminmenuComponent implements OnInit {

  constructor(private router: Router,
              private storageService: StorageService) {
  }

  ngOnInit() {
  }

  navigateToUsers(roleID): void {
    this.storageService.saveRoleId(roleID);
    this.router.navigate(['/users']);
  }
}
