import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthorizationInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    let jwtToken = localStorage.getItem("jwtToken");

    if(jwtToken == null) {
      return next.handle(request);
    }

    const req1 = request.clone({
      headers: request.headers.set("Authorization",`Bearer ${jwtToken}`)
    });

    return next.handle(req1);


  }
}
