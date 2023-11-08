package com.example.order_jpa.repository;

import com.example.order_jpa.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JPAUserRepository {
    @PersistenceContext
    private EntityManager em;
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    public User findById(Long userId) {
        return em.find(User.class, userId);
    }

    public void save(User user) {
        em.persist(user);
    }

    public void delete(User user) {
        em.remove(user);
    }
}
