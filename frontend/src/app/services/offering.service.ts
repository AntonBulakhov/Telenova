import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {OfferingTypeModel} from "../models/offeringtype.model";
import {ValueMeasureModel} from "../models/valuemeasure.model";
import {GroupedOfferingsModel} from "../dto/groupedofferings.model";
import {OfferingModel} from "../models/offering.model";

@Injectable({
  providedIn: 'root'
})
export class OfferingService {

  constructor(private http: HttpClient) {
  }

  getGroupedOfferingsByType(): Observable<GroupedOfferingsModel> {
    return this.http.get<GroupedOfferingsModel>("/api/offering/grouped");
  }

  getAllOfferingTypes(): Observable<OfferingTypeModel[]> {
    return this.http.get<OfferingTypeModel[]>("/api/offering/types");
  }

  getAllValueMeasures(): Observable<ValueMeasureModel[]> {
    return this.http.get<ValueMeasureModel[]>("/api/offering/value/measures");
  }

  createOffering(offering: OfferingModel): Observable<boolean> {
    return this.http.post<boolean>("/api/offering", offering);
  }

  deleteOffering(id: string): any {
    return this.http.delete("/api/offering/" + id);
  }
}
