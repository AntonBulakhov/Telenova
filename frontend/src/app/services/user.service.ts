import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  saveUser(user: UserModel): Observable<boolean>{
    return this.http.post<boolean>("/api/user", user);
  }

  getFullUserById(id: string): Observable<UserModel> {
    return this.http.get<UserModel>("/api/user/profile/" + id);
  }
}
