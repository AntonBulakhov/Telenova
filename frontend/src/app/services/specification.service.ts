import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SpecificationModel} from "../models/specification.model";

@Injectable({
  providedIn: 'root'
})
export class SpecificationService {

  constructor(private http: HttpClient) { }

  getAllSpecifications(): Observable<SpecificationModel[]> {
    return this.http.get<SpecificationModel[]>("/api/specification/all")
  }
}
