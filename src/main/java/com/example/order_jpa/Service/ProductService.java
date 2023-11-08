package com.example.order_jpa.Service;

import com.example.order_jpa.dto.ProductUpdateDto;
import com.example.order_jpa.dto.UserUpdateDto;
import com.example.order_jpa.entity.Product;
import com.example.order_jpa.entity.User;
import com.example.order_jpa.repository.JPAProductRepository;
import com.example.order_jpa.repository.JPAUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Product> getAllProduct() {
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
