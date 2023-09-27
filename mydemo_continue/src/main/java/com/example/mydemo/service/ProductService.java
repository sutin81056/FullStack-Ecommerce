package com.example.mydemo.service;

import com.example.mydemo.model.entity.Category;
import com.example.mydemo.model.entity.Product;
import com.example.mydemo.model.entity.dto.ProductDTO;
import com.example.mydemo.repository.CategoryRepository;
import com.example.mydemo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return new Product();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, String name, Integer price, Integer inventory, String categories) {
        Optional<Product> result = productRepository.findById(id);
        Product p = result.get();

        p.setName(name);
        p.setPrice(price);
        p.setInventory(inventory);
        p.getCategories().clear();
        p = addCategoriesToProduct(p, categories);

        return productRepository.save(p);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    // currently use for adding a product
    public void saveProductToDB(MultipartFile file, String name, Integer price, Integer inventory, String categories)
    {
        Product p = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")) {
            System.out.println("not a valid file");
        }

        try {
            p.setImage(resizeImageForUse(Base64.getEncoder().encodeToString(file.getBytes()),400,400));
        } catch (IOException e) {
            e.printStackTrace();
        }

        p.setName(name);
        p.setPrice(price);
        p.setInventory(inventory);
        p = addCategoriesToProduct(p, categories);
        productRepository.save(p);
    }

    // create category_product relations
    private Product addCategoriesToProduct(Product product ,String categories) {
        String [] cates = categories.split(",");
        Category category = null;
        for(String s : cates) {
                category = categoryRepository.findById(Long.parseLong(s)).get();
            product.getCategories().add(category);
        }
        return product;
    }

    public String resizeImageForUse(String src,int width, int height) {
        BufferedImage image = base64ToBufferedImage(src);
        try {
            image = resizeImage(image, width,height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return bufferedImageTobase64(image);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    private  BufferedImage resizeImage(BufferedImage image , int width , int height) throws IOException {
        ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
        try {
            Thumbnails.of(image).size(width, height).outputFormat("JPEG").outputQuality(1).toOutputStream(outputstream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = outputstream.toByteArray();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        return ImageIO.read(inputStream);
    }
    private BufferedImage base64ToBufferedImage(String base64Img) {
        BufferedImage image = null;
        byte[] decodedBytes = Base64.getDecoder().decode(base64Img);

        try {
            image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private String bufferedImageTobase64(BufferedImage image ) throws UnsupportedEncodingException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "JPEG", Base64.getEncoder().wrap(out));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString(StandardCharsets.ISO_8859_1.name());
    }

    public List<ProductDTO> getAllProductsExceptCategories() {
        return productRepository.findAllProductDTOs();
    }

    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        List<Long> pId = productRepository.findProductIdByCategoryId(categoryId);
        return productRepository.findProductDTOsById(pId);
    }
        // 1
//        Long catId = categoryRepository.findCategoryIdByName(category);
//        List<Long> pId = productRepository.findProductIdByCategoryId(catId);
//        return productRepository.findAllById(pId);
        // 2
//        Specification<Product> combinations =
//                Objects.requireNonNull(Specification.where(ProductSpecs.withCategory(category)));
//        return productRepository.findAll((Sort) combinations);
        // 3
//        Specification<Product> spec = ProductSpecs.withCategory(category);
//        return productRepository.findAll((Sort) spec);
        // 4
//        Specification<Product> spec = ProductSpecs.productsByCategoryId(categoryId);
//        return productRepository.findAll((Sort) spec);
    }
//    public List<ProductResponse> getAll(String name, Integer price, String image) {
//
//
//        return productRepository.findAll();
//    }


