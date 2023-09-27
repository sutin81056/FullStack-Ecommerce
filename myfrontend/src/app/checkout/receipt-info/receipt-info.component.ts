import { Component } from '@angular/core';
import { appPath } from 'src/app/app-path.const';

@Component({
  selector: 'app-receipt-info',
  templateUrl: './receipt-info.component.html',
  styleUrls: ['./receipt-info.component.css']
})
export class ReceiptInfoComponent {
  path = appPath;
}
