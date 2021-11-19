package com.sa.tickestoque.product;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(path = "/")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping(path = "/find/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id ){
        Product product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PostMapping(path = "/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product  product){
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }
    @PutMapping(path = "/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product updateProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id ){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
