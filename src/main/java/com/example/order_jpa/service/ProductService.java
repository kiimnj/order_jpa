package com.example.order_jpa.service;

import com.example.order_jpa.dto.ProductUpdateDto;
import com.example.order_jpa.entity.Product;
import com.example.order_jpa.repository.JPAProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final JPAProductRepository productRepository;

//    @Autowired
//    public UserService(JPAUserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

//    @Transactional(readOnly = true)
    public Product getProductInfo(Long productId) {
        return productRepository.findById(productId);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(productUpdateDto.getProductId());
        product.setName(productUpdateDto.getName());
        product.setPrice(productUpdateDto.getPrice());
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product findProduct = productRepository.findById(id);
        productRepository.delete(findProduct);
    }
}
