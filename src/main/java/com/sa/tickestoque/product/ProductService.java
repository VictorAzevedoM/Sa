package com.sa.tickestoque.product;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }
    public Product updateProduct(Product product){
        return productRepository.save(product);
    }
    public Product findProductById(Long id ){
        return productRepository.findProductById(id)
                .orElseThrow(()->new UsernameNotFoundException("Produto com o id" + id + "não encontrado"));

    }
    public void deleteProduct(Long id){
        productRepository.deleteProductById(id);
    }

}
