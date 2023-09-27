import { Component } from '@angular/core';
import { appPath } from 'src/app/app-path.const';
@Component({
  selector: 'app-payment-info',
  templateUrl: './payment-info.component.html',
  styleUrls: ['./payment-info.component.css']
})
export class PaymentInfoComponent {
  path = appPath;
}
