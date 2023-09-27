package com.example.mydemo.controller;

import com.example.mydemo.exception.AppException;
import com.example.mydemo.model.entity.Product;
import com.example.mydemo.service.CategoryService;
import com.example.mydemo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping(value="/admin")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/lunchAllProductsPage")
    public String showAllProducts(Model model) {
        try {
            model.addAttribute("products", productService.getAllProducts());
            return "/allProducts";
        } catch (Exception e) {
            log.error("An error occurred while showing products: {}", e.getMessage(), e);
            return "errorPage";
        }
    }

    @GetMapping("/lunchAddProductWithPicturePage")
    public String lunchAddProductWithPicturePage(Model model) {
        try {
            model.addAttribute("product", new Product());
            model.addAttribute("categories", categoryService.getAllCategories());
            return "addProductWithPicture";
        } catch (Exception e) {
            log.error("An error occurred while showing products: {}", e.getMessage(), e);
            return "errorPage";
        }
    }

    @PostMapping("/addProductWithPicture")
    public String saveProduct(@RequestParam("file") MultipartFile file,
                              @RequestParam("name") String name,
                              @RequestParam("price") Integer price,
                              @RequestParam("inventory") Integer inventory,
                              @RequestParam("categories") String categories)
    {
        try {
            productService.saveProductToDB(file, name, price, inventory, categories);
            return "redirect:/admin/lunchAllProductsPage";
        } catch (Exception e) {
            log.error("An error occurred while saving the product: {}", e.getMessage(), e);
            return "errorPage";
        }
    }

    @GetMapping("/editProduct/{id}")
    public String lunchEditPage(Model model, @PathVariable("id") Long id) {
        try {
            model.addAttribute("product", productService.findProductById(id));
            model.addAttribute("categories", categoryService.getAllCategories());
            return "editProduct";
        } catch (Exception e) {
            log.error("An error occurred while preparing to edit the product: {}", e.getMessage(), e);
            return "errorPage";
        }
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam Integer price,
                                @RequestParam Integer inventory,
                                @RequestParam String categories) {
        try {
            productService.updateProduct(id, name, price, inventory, categories);
            return "redirect:/admin/lunchAllProductsPage";
        } catch (Exception e) {
            log.error("An error occurred while updating the product: {}", e.getMessage(), e);
            return "errorPage";
        }
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        try {
            productService.deleteProductById(id);
            return "redirect:/admin/lunchAllProductsPage";
        } catch (Exception e) {
            log.error("An error occurred while deleting the product: {}", e.getMessage(), e);
            return "errorPage";
        }
    }
}


//    @GetMapping("/getProductById/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        Optional<Product> productObj = productService.findById(id);
//        if (productObj.isPresent()) {
//            return new ResponseEntity<>(productObj.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }
//    @GetMapping("/lunchAddProductPage")
//    public String lunchAddProductPage(Model model) {
//        model.addAttribute("product", new Product());
//        return "addProduct";
//    }


//    @PostMapping("/addProduct")
//    public String createProduct(@ModelAttribute Product product) {
//        productService.addProduct(product);
//        return "redirect:/lunchAllProductsPage";
//    }


//    8/28 圖片上傳測試
//    @GetMapping("/getProductImage/{id}")
//    public ResponseEntity<byte[]> getProductImage(Model model, @PathVariable Long id) {
//        Optional<Product> productObj = Optional.ofNullable(productService.findProductById(id));
//        if (productObj.isPresent()) {
//            byte[] image = productObj.get().getImage();
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_JPEG); // 根據實際情況設定圖片類型
//            return new ResponseEntity<>(image, headers, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @GetMapping("/ProductChangePage")
//    public String adminPage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
//        int pageSize = 5; // 每頁顯示5筆資料
//        Page<Product> productPage = productService.getProductsPage(page, pageSize);
//
//        model.addAttribute("products", productPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", productPage.getTotalPages());
//
//        return "admin";
//    }

//    @PostMapping("/addProduct")
//    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
//        try {
//            Product productObj = productService.save(product);
//            return new ResponseEntity<>(productObj, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//
//
//    @PostMapping("/updateProduct/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
//        try {
//            Optional<Product> productData = productService.findById(id);
//            if (productData.isPresent()) {
//                Product updatedProductData = productData.get();
//                updatedProductData.setName(product.getName());
//                updatedProductData.setPrice(product.getPrice());
//                updatedProductData.setInventory(product.getInventory());
//
//                Product productObj = productService.save(updatedProductData);
//                return new ResponseEntity<>(productObj, HttpStatus.CREATED);
//            }
//
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/deleteProductById/{id}")
//    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
//        try {
//            productService.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

