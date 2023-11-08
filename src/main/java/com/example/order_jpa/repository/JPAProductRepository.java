package com.example.order_jpa.repository;

import com.example.order_jpa.entity.Product;
import com.example.order_jpa.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JPAProductRepository {
    @PersistenceContext
    private EntityManager em;
    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    public Product findById(Long productId) {
        return em.find(Product.class, productId);
    }

    public void save(Product product) {
        em.persist(product);
    }

    public void delete(Product product) {
        em.remove(product);
    }
}
