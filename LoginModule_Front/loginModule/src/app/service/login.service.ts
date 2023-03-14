import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginInfo } from '../model/login-info';
import { User } from '../model/user';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  public generateToken(loginInfo:LoginInfo) {
   return this.http.post(`${baseUrl}/generate-token`,loginInfo);
  }

  public getToken() {
    return localStorage.getItem("jwtToken");
  }


  public getCurrentUser() {
    return this.http.get(`${baseUrl}/current-user`);
  }



  public loginUser(jwtToken:string) {
    localStorage.setItem("jwtToken",jwtToken);
    return true;
  }

  public isLoggedIn() {
    let jwtToken = localStorage.getItem("jwtToken");
    if(jwtToken == undefined || jwtToken == "" || jwtToken==null)
      return false;
    return true;
  }

  public setUser(user:User) {
    localStorage.setItem("user",JSON.stringify(user));
  }


  public logout() {
    localStorage.removeItem("user");
    localStorage.removeItem("jwtToken");

    return true;

  }

  public getUser() {
    let userStr = localStorage.getItem("user");

    if(userStr != null) {
      return JSON.parse(userStr);
    }

    this.logout();
    return null;

  }

  public getUserRole() {
    // console.log(this.getUser().authorities[0].authority);
    return this.getUser().authorities[0].authority;
  }

}
