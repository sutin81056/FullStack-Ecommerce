import { Component } from '@angular/core';
import { appPath } from 'src/app/app-path.const';

@Component({
  selector: 'app-customer-info',
  templateUrl: './customer-info.component.html',
  styleUrls: ['./customer-info.component.css']
})
export class CustomerInfoComponent {
  path = appPath;
}
