import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { config } from 'src/config/local';

@Injectable({ providedIn: 'root' })
export class ProductService {

  productUrl = `${config.apiUrl}/api/product`;
  categoryUrl = `${config.apiUrl}/api/category`;

  constructor(private http: HttpClient) {
  }

  getProducts() {
    return this.http.get(`${this.productUrl}/all`);
  }

  getProductsByCategoryId(categoryId: number) {
    return this.http.get(`${this.productUrl}/productsByCategory?categoryId=${categoryId}`)
  }

  getCategories() {
    return this.http.get(`${this.productUrl}/categories`)
  }

}
