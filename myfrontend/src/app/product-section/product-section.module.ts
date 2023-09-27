import { NgxPaginationModule } from 'ngx-pagination';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductSectionRoutingModule } from './product-section-routing.module';
import { ProductSectionComponent } from './product-section.component';
import { ProductListComponent } from './product-list/product-list.component';

import { ProductItemModule } from '../shared/product-item/product-item.module';
import { ProductService } from '../services/product.service';

@NgModule({
  declarations: [
    ProductSectionComponent,
    ProductListComponent,
  ],
  providers: [
    ProductService
  ],
  imports: [
    CommonModule,
    ProductSectionRoutingModule,
    ProductItemModule,
    NgxPaginationModule
  ]
})
export class ProductSectionModule { }
