import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../../services/storage/storage.service";
import {ServService} from "../../../services/serv.service";
import {ServiceModel} from "../../../models/service.model";
import {AddressModel} from "../../../models/address.model";
import {ServiceStatusModel} from "../../../models/servicestatus.model";
import {Router} from "@angular/router";
import {AuthService} from "../../../services/security/auth.service";

@Component({
  selector: 'app-internetsubmit',
  templateUrl: './internetsubmit.component.html',
  styleUrls: ['./internetsubmit.component.css']
})
export class InternetsubmitComponent implements OnInit {

  public address: AddressModel = new AddressModel();
  private serv: ServiceModel = new ServiceModel();

  private serviceStatuses: ServiceStatusModel[];

  constructor(private storageService: StorageService,
              private servService: ServService,
              private router: Router,
              private auth: AuthService) {
  }

  ngOnInit() {
    if(this.auth.user.role.id != '4') {
      this.router.navigate(['/']);
    }
    this.serv.offerId = this.storageService.getOfferId();
    this.servService.getAllServiceStatuses().subscribe(value => {
      this.serviceStatuses = value as ServiceStatusModel[];
    });
  }

  onSubmit(): void {
    this.serv.userId = this.auth.user.id;
    this.serv.address = this.address;
    this.serv.serviceStatus = this.getNewStatus('1');
    this.servService.createInternetService(this.serv).subscribe(value => {
      this.router.navigate(['/']);
    });
  }

  public getNewStatus(id: string): ServiceStatusModel {
    return this.serviceStatuses.find(obj => obj.id == id);
  }
}
