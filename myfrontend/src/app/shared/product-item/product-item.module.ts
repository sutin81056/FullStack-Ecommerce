import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductItemComponent } from './product-item.component';
import { ProductService } from 'src/app/services/product.service';



@NgModule({
  declarations: [
    ProductItemComponent
  ],
  imports: [
    CommonModule
  ],
  // 此寫法可拋棄(after Angular6)
  providers: [ProductService],

  // 匯出才能使用
  exports: [
    ProductItemComponent
  ]
})

export class ProductItemModule { }
