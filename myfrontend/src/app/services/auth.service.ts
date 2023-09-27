import { Injectable } from '@angular/core';
import { config } from 'src/config/local';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
// ${config.apiUrl}
@Injectable({
  providedIn: 'root'
})

// sessionStorage看分頁跟瀏覽器，localStorage看本地端
export class AuthService {
  loginUrl = `${config.authUrl}/user/login`;
  validateUrl = `${config.authUrl}/user/forUser`;
  // declare http
  private isAuthenticated;
  private message = '';

  constructor(private http: HttpClient) {
    const storedValue = sessionStorage.getItem('isAuthenticated');
    this.isAuthenticated = (storedValue === 'true');
  }

  getAuthToken(): string | null {
    return window.sessionStorage.getItem("auth_token");
  }

  setAuthToken(token: string | null): void {
    if (token !== null) {
      window.sessionStorage.setItem("auth_token", token);
    } else {
      window.sessionStorage.removeItem("auth_token");
    }
  }
  removeAuthToken(): void {
    window.sessionStorage.removeItem("auth_token");
  }
  /////////////
  /////////////
  requestPost(data: any): Observable<any> {
    var headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    if (this.getAuthToken() !== null) {
      headers.append("Authorization", "Bearer " + this.getAuthToken());
    }
    // console.log(headers)
    return this.http.post(this.loginUrl, {
      username: data.username,
      password: data.password
    });
  }
  requestGet(): Observable<any> {
    // headers一但宣告了就不能動，未來一定要改成interceptor的實作方式
    var headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': "Bearer "+this.getAuthToken()
    });
    return this.http.get(this.validateUrl);
  }

  // message is a value to justify a user is login or not
  // this is getter and setter
  getMessage(): string | null {
    return this.message;
  }

  setMessage(message: string | null): void {
    if (message !== null) {
      this.message = message;
    } else {
      this.message = '';
    }
  }
  setMessageToNull(): void {
    this.message = '';
  }
  login() {
    this.isAuthenticated = true;
    sessionStorage.setItem('isAuthenticated', 'true')
  }

  logout() {
    this.isAuthenticated = false;
    sessionStorage.removeItem('isAuthenticated');
    this.removeAuthToken();

  }

  isLoggedIn() {
    return this.isAuthenticated;
  }
}

  // userLoggedin(data: any): boolean {
  //   this.requestPost(data)
  //   .subscribe(
  //     res => {
  //       this.setAuthToken(res.token);
  //     },
  //     err => {
  //       console.log(err.error)
  //     }
  //   );
  //   if (this.getAuthToken()!==null){
  //     return true;
  //   }
  //   return false;
  // }

  // isLoggedin(): boolean {
  //   this.requestGet({})
  //   .subscribe(
  //     res => {
  //       this.setAuthToken(res.token);
  //     },
  //     err => {
  //       console.log(err.error)
  //       return false;
  //     }
  //   );
  //   return true;
  // }
