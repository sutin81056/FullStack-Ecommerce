import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor{

  constructor(private authService: AuthService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {

    const authToken = this.authService.getAuthToken();
    var headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    if (authToken !== null) {
      headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${authToken}`
      });
    }
    // console.log(authToken)
    // console.log(headers)
    const authReq = request.clone({ headers });

    return next.handle(authReq);
  }
}
  // 詢問是用authService的服務好，還是直接取得sessionStorage的服務好?
  // private getAuthToken(): string | null{
  //   return sessionStorage.getItem("auth_token");
  // }

