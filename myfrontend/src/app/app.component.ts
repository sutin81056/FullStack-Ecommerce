import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { AuthService } from './services/auth.service';
// Constant
import { appPath } from './app-path.const';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'myfrontend';

  path = appPath;

  constructor(private router: Router, private authService: AuthService) { }

  isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  logout(): void {
    const isConfirmed = window.confirm('確定登出嗎?');
    if (isConfirmed) {
      this.authService.logout();
      this.router.navigate([this.path.home]);
      window.alert('已登出');
    }
  }

}


  // isHomeActive(): boolean {
  //   return this.router.url === '/home';
  // }
