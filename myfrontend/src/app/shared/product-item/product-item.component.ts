import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductService } from 'src/app/services/product.service';
@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent implements OnInit {
  @Input() sectionClass: string = 'col-md-6 py-3';
  @Input() homeClass: string = 'col-md-4 py-3';

  @Input() categoryId: number | null = null;
  // products$: Observable<any> | undefined;
  products: any;

  constructor(public productService: ProductService) {
  }

  ngOnInit() {
    // this.products$ = this.productService.getProducts();
    this.productService.getProducts().subscribe(result => {
      this.products = result;
    });
  }
  ngOnChanges(changes: SimpleChanges) {
    if (changes['categoryId'] && this.categoryId !== null) {
      console.log('categoryId changed to ', this.categoryId);
      this.productService.getProductsByCategoryId(this.categoryId).subscribe((products) => {
        this.products = products;
      });
    } else {
      console.log('categoryId is null or unexpect error');
      this.productService.getProducts().subscribe(result => {
        this.products = result;
      });
    }
  }

  // ngOnInit(): void {
  //   this.productService.getProducts().subscribe(result => {
  //     this.products = result;
  //   },
  //   // error參數
  //   (error) => {
  //     console.log(error+"error 500")
  //   });
  // }
// ngOnChange的方法1
// ngOnChanges(changes: SimpleChanges) {
//   if (changes['categoryId'] && this.categoryId !== null) {
//     console.log('categoryId changed:', this.categoryId);
//     this.products = this.productService.getProductsByCategoryId(this.categoryId);
//   }
}


