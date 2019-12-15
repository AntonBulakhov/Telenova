import {Component, OnInit} from '@angular/core';
import {ServService} from "../../../services/serv.service";
import {InternetServiceOfferModel} from "../../../dto/iserviceoffer.model";
import {ServiceStatusModel} from "../../../models/servicestatus.model";
import {ServiceModel} from "../../../models/service.model";
import {AuthService} from "../../../services/security/auth.service";
import {Constants} from "../../../constants/constants";

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  private services: InternetServiceOfferModel[];

  private statuses: ServiceStatusModel[];

  private serviceToPay: string;

  constructor(private servService: ServService,
              private auth: AuthService) {
  }

  ngOnInit() {
    switch (this.auth.user.role.id) {
      case Constants.EMPLOYEE_ROLE_ID:
        this.servService.getInternetServicesByStatus(Constants.NEW_SERVICE_STATUS_ID).subscribe(value => {
          this.services = value as InternetServiceOfferModel[];
        });
        break;
      case Constants.CLIENT_ROLE_ID:
        this.servService.getInternetServicesByStatus(Constants.CONFIRMED_SERVICE_STATUS_ID).subscribe(value => {
          this.services = value as InternetServiceOfferModel[];
        });
        break;
    }
    this.servService.getAllServiceStatuses().subscribe(value => {
      this.statuses = value as ServiceStatusModel[];
    });
  }

  setStatus(serviceId: string, statusId: string): void {
    let service: ServiceModel = new ServiceModel();
    service.id = serviceId;
    service.serviceStatus = this.getStatus(statusId);
    this.servService.setServiceStatus(service).subscribe(value => {
      this.ngOnInit();
    });
  }

  deleteInternetService(serviceId: string): void {
    this.servService.deleteInternetService(serviceId).subscribe(value => {
      this.ngOnInit();
    });
  }

  setServiceToPay(serviceId: string): void {
    this.serviceToPay = serviceId;
  }

  public getStatus(id: string): ServiceStatusModel {
    return this.statuses.find(obj => obj.id == id);
  }
}
