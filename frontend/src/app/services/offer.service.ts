import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {OfferDTOModel} from "../dto/offerdto.model";
import {OfferStatusModel} from "../models/offerstatus.model";
import {InternetOfferDtoModel} from "../dto/iOfferdto.model";

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  constructor(private http: HttpClient) {
  }

  getAllOfferStatuses(): Observable<OfferStatusModel[]> {
    return this.http.get<OfferStatusModel[]>("/api/offer/statuses");
  }

  getMobileOffers(): Observable<OfferDTOModel[]> {
    return this.http.get<OfferDTOModel[]>("/api/offer/mobile/all");
  }

  getInternetOffers(): Observable<InternetOfferDtoModel[]> {
    return this.http.get<InternetOfferDtoModel[]>("/api/offer/internet/all");
  }

  createMobileOffer(offerDTOModel: OfferDTOModel): Observable<boolean> {
    return this.http.post<boolean>("/api/offer/mobile", offerDTOModel);
  }

  createInternetOffer(offer: InternetOfferDtoModel): Observable<boolean> {
    return this.http.post<boolean>("/api/offer/internet", offer);
  }
}
