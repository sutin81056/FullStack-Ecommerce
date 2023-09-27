import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CheckoutRoutingModule } from './checkout-routing.module';
import { CheckoutComponent } from './checkout.component';
import { CustomerInfoComponent } from './customer-info/customer-info.component';
import { PaymentInfoComponent } from './payment-info/payment-info.component';
import { ReceiptInfoComponent } from './receipt-info/receipt-info.component';
import { AuthGuard } from '../auth.guard';
import { AuthService } from '../services/auth.service';


@NgModule({
  declarations: [
    CheckoutComponent,
    CustomerInfoComponent,
    PaymentInfoComponent,
    ReceiptInfoComponent,
  ],
  providers: [
    AuthService,
    AuthGuard
  ],
  imports: [
    CommonModule,
    CheckoutRoutingModule
  ]
})
export class CheckoutModule { }
