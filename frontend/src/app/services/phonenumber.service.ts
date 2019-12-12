import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PhoneNumberModel} from "../models/phonenumber.model";

@Injectable({
  providedIn: 'root'
})
export class PhoneNumberService {

  constructor(private http: HttpClient) { }

  generateNumber(): Observable<PhoneNumberModel> {
    return this.http.get<PhoneNumberModel>("/api/phone/generate");
  }
}
