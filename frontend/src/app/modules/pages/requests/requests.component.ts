import {Component, OnInit} from '@angular/core';
import {ServService} from "../../../services/serv.service";
import {InternetServiceOfferModel} from "../../../dto/iserviceoffer.model";

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  private services: InternetServiceOfferModel[];

  constructor(private servService: ServService) {
  }

  ngOnInit() {
    this.servService.getInternetServicesByStatus('1').subscribe(value => {
      this.services = value as InternetServiceOfferModel[];
    });
  }

}
