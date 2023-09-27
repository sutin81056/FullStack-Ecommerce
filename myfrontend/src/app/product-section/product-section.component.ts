import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-section',
  templateUrl: './product-section.component.html',
  styleUrls: ['./product-section.component.css']
})
export class ProductSectionComponent implements OnInit{
  categories$: Observable<any> | undefined;

  selectedCategoryId: number | null = null;

  constructor(public productService: ProductService) {
  }

  selectCategory(categoryId: number) {
    this.selectedCategoryId = categoryId;
  }
  setCategoryToNull() {
    this.selectedCategoryId = null;
  }

  // async cate
  ngOnInit(): void {
    this.categories$ = this.productService.getCategories();
  }

}
