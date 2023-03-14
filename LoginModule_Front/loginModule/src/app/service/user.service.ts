import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  // method for creating new user and persisting user into DB
  public registerUser(user:User) {
    return this.http.post( `${baseUrl}/user/`,user);
  }



}
