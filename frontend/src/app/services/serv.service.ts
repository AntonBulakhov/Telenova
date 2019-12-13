import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ServiceStatusModel} from "../models/servicestatus.model";
import {NewServiceModel} from "../dto/newservice.model";
import {ProfileMobileOfferModel} from "../dto/profilemobileoffer.model";
import {BalanceModel} from "../models/balance.model";

@Injectable({
  providedIn: 'root'
})
export class ServService {

  constructor(private http: HttpClient) {
  }

  getAllServiceStatuses(): Observable<ServiceStatusModel[]> {
    return this.http.get<ServiceStatusModel[]>("/api/service/statuses");
  }

  createMobileService(mobileService: NewServiceModel): Observable<boolean> {
    return this.http.post<boolean>("/api/service/mobile", mobileService);
  }

  getMobileServiceByUserId(id: string): Observable<ProfileMobileOfferModel[]> {
    return this.http.get<ProfileMobileOfferModel[]>("/api/service/mobile/user/" + id);
  }

  fillBalance(balance: BalanceModel): Observable<boolean> {
    return this.http.post<boolean>("/api/service/balance", balance);
  }
}
