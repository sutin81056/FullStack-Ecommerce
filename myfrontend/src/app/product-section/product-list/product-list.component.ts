import { Component, Input, SimpleChanges } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent {

  // products$: Observable<any> | undefined;

  // products
  products: any;

  // @Input() to product-section and decide to get category or not
  @Input() selectedCategoryId: number | null = null;

  // pagination variables
  currentPage: number = 1;
  itemsPerPage: number = 4;
  totalItems: number = 1000;
  selectedPage: number = 1;


  constructor(public productService: ProductService) {
  }
  // 初始化
  ngOnInit() {
    // this.products$ = this.productService.getProducts();
    this.products = [];
    this.loadProducts()
  }

  // 有變化
  ngOnChanges(changes: SimpleChanges) {
    if (changes['selectedCategoryId'] && this.selectedCategoryId !== null) {
      // console.log('categoryId changed to ', this.selectedCategoryId);
      this.loadProductsByCategoryId(this.selectedCategoryId);
      this.currentPage = 1;
      this.selectedPage = 1;
    } else {
      // console.log('categoryId is null or unexpect error');
      this.loadProducts();
    }
  }

  // get Products from service
  loadProducts() {
    this.productService.getProducts().subscribe(result => {
      this.products = result;
      this.totalItems = this.products.length;
    });
  }
  loadProductsByCategoryId(categoryId: number) {
    this.productService.getProductsByCategoryId(categoryId).subscribe(result => {
      this.products = result;
      this.totalItems = this.products.length;
    });
  }


  // pagination
  getDisplayedProducts() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    return this.products.slice(startIndex, endIndex);
  }
  previousPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.selectedPage--;
    }
  }

  nextPage() {
    if (this.currentPage < this.getTotalPages()) {
      this.currentPage++;
      this.selectedPage++;
    }
  }

  goToPage(page: number) {
    this.currentPage = page;
    this.selectedPage = page;
  }

  getTotalPages() {
    return Math.ceil(this.totalItems / this.itemsPerPage);
  }

  getPages() {
    // 內容不改，index改成i+1 => 0, 1, 2 變成 1, 2, 3
    return Array.from({ length: this.getTotalPages() }, (_, i) => i + 1);
  }

}
