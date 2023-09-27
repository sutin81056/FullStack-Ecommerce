import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import { Observable, of } from 'rxjs';
import { AuthService } from './services/auth.service';
import { appPath } from './app-path.const';
import { switchMap, map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard {
  path = appPath;

  constructor(private authService: AuthService, private router: Router) {}
// 解決canActive與UI操作上不同步的問題: (1.登入要點兩次、2.清掉local storage沒有立即登出，也得點兩次)

// 問題與非同步操作的執行順序有關。
// 因為在 canActivate 方法中發起了一個非同步請求，並且在請求完成之前就執行了 if (this.message === 'ok') 條件判斷。
// 導致在請求完成之前，this.message的值還沒有被設置為伺服器響應的值。
// 為了解決這個問題，得將條件判斷移到 subscribe 方法內部，確保在請求成功後再進行判斷
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.authService.requestGet().pipe(
      map((res) => {
        this.authService.setMessage(res.message);
        console.log("subscribe get message...");
        if (this.authService.getMessage() === 'ROLE_USER') {
          this.authService.setMessageToNull();
          console.log("get success! navigate to checkout. ");
          return true;
        } else {
          this.router.navigate([this.path.login]);
          console.log("get failed! unexpected error. ");
          return false;
        }
      }),
      catchError((err) => {
        console.log("get failed! unexpected error. ");
        // console.error("get failed! unexpected error. ", err);
        this.router.navigate([this.path.login]);
        return of(false);
      })
    );
  }
}

