import { Component } from '@angular/core';
import { appPath } from '../app-path.const';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent {
  path = appPath;
}
