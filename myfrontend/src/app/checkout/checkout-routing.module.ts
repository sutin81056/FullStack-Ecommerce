import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Constant
import { appPath } from '../app-path.const';

// Component
import { CheckoutComponent } from './checkout.component';
import { CustomerInfoComponent } from './customer-info/customer-info.component';
import { PaymentInfoComponent } from './payment-info/payment-info.component';
import { ReceiptInfoComponent } from './receipt-info/receipt-info.component';
import { AuthGuard } from '../auth.guard';

const routes: Routes = [
  {
    path: '',
    component: CheckoutComponent,
    children: [
      {
        path: '',
        redirectTo: appPath.checkoutFlow.customerInfo,
        pathMatch: 'full'
      },
      {
        path: appPath.checkoutFlow.customerInfo,
        component: CustomerInfoComponent,
        canActivate: [AuthGuard],
      },
      {
        path: appPath.checkoutFlow.paymentInfo,
        component: PaymentInfoComponent,
        canActivate: [AuthGuard],
      },
      {
        path: appPath.checkoutFlow.receiptInfo,
        component: ReceiptInfoComponent,
        canActivate: [AuthGuard],
      },
      {
        path: '**',
        redirectTo: appPath.checkoutFlow.customerInfo,
        pathMatch: 'full'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CheckoutRoutingModule { }
