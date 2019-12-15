import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel} from "../models/user.model";
import {UserStatusModel} from "../models/userstatus.model";
import {RoleModel} from "../models/role.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  getAllUserStatuses(): Observable<UserStatusModel[]> {
    return this.http.get<UserStatusModel[]>("/api/user/status/all");
  }

  getAllUserRoles(): Observable<RoleModel[]> {
    return this.http.get<RoleModel[]>("/api/user/role/all");
  }

  saveUser(user: UserModel): Observable<boolean>{
    return this.http.post<boolean>("/api/user", user);
  }

  getFullUserById(id: string): Observable<UserModel> {
    return this.http.get<UserModel>("/api/user/profile/" + id);
  }
}
