import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {StorageService} from "../../../services/storage/storage.service";
import {AuthService} from "../../../services/security/auth.service";

@Component({
  selector: 'app-adminmenu',
  templateUrl: './adminmenu.component.html',
  styleUrls: ['./adminmenu.component.css']
})
export class AdminmenuComponent implements OnInit {

  constructor(private router: Router,
              private storageService: StorageService,
              private auth: AuthService) {
  }

  ngOnInit() {
    if(this.auth.user.role.id == '4') {
      this.router.navigate(['/']);
    }
  }

  navigateToUsers(roleID): void {
    this.storageService.saveRoleId(roleID);
    this.router.navigate(['/users']);
  }
}
