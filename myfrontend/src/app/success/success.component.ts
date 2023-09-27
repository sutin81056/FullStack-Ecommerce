import { Component } from '@angular/core';
import { appPath } from '../app-path.const';
@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.css']
})
export class SuccessComponent {
  path = appPath;
}
