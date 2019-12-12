import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ServiceStatusModel} from "../models/servicestatus.model";
import {NewServiceModel} from "../dto/newservice.model";

@Injectable({
  providedIn: 'root'
})
export class ServService {

  constructor(private http: HttpClient) { }

  getAllServiceStatuses(): Observable<ServiceStatusModel[]> {
    return this.http.get<ServiceStatusModel[]>("/api/service/statuses");
  }

  createMobileService(mobileService: NewServiceModel): Observable<boolean> {
    return  this.http.post<boolean>("/api/service/mobile", mobileService);
  }
}
