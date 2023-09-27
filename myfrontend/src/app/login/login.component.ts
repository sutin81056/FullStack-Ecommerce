import { AuthService } from '../services/auth.service';
import { appPath } from './../app-path.const';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AbstractControl, ValidatorFn } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  path = appPath
  // 告訴使用者錯誤(只會告訴她: "請輸入有效的帳號或密碼!" )
  loginErrorMessage = ''

  // 紅色驚嘆號 和 紅框
  usernameValid = true;
  passwordValid = true;

  constructor(private authService: AuthService, private router: Router) { }

  username = '';
  password = '';

  onLogin(): void {

    // If login form contains special char then return
    if (this.containsSpecialCharacters(this.username) || this.containsSpecialCharacters(this.password)) {
      this.usernameValid = false;
      this.passwordValid = false;
      this.loginErrorMessage = "請輸入有效的帳號或密碼!";
      return;
    }

    const data = {
      username: this.username,
      password: this.password
    }

    this.authService.requestPost(data)
    .subscribe(
      res => {
        this.authService.setAuthToken(res.token);
        this.authService.login();
        this.router.navigate([this.path.checkout]);
        console.log("onLogin triggerred and requestPost success");
      },
      err => {
        if (err.status === 404) {
          this.loginErrorMessage = "帳號或密碼有誤!";
          this.usernameValid = false;
          this.passwordValid = false;
          this.password = '';
        } else {
          this.loginErrorMessage = "發生未知錯誤! 請聯繫系統管理員";
        // console.log(err)
        }
      }
    );
  }

  containsSpecialCharacters(inputString: string): boolean {
    // 可以輸入 "@" 和 "."
    const validCharactersRegex = /^[A-Za-z0-9@.]+$/;

    return !validCharactersRegex.test(inputString);
  }

  // 使用者輸入時清掉紅框和紅驚嘆號
  resetAccountValidation(): void {
    this.usernameValid = true;
  }
  resetPasswordValidation(): void {
    this.passwordValid = true;
  }

}


  // onLogin() {

  //   this.authService.login(username, password);

  // }

  // onLogin(): void {
  //   const data = {
  //     username: this.username,
  //     password: this.password
  //   }
  //   this.tokenService.request(data)
  //   .subscribe(
  //     res => {
  //       this.tokenService.setAuthToken(res.token);

  //       console.log("success")
  //     },
  //     err => console.log(err.error)
  //   );
  // }

  // 原containsSpecialCharacters邏輯
  // const forbidden= /[!#$%^&*()_+{}-\[\]:;<>,?~\\]\"\'/;
