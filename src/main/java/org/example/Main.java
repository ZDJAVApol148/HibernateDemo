package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("my-persistence-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Product product = new Product();
        product.setName("TV");
        product.setDescription("Widescreen TV");
        product.setProductPrice(new BigDecimal("2999.99"));

        entityManager.persist(product);

        entityManager.getTransaction().commit();

        Query query = entityManager.createQuery("select i from Product i");
        List<Product> products = query.getResultList();

        for (Product p: products) {
            System.out.println(
                            p.getId() + " " +
                            p.getName() + " " +
                            p.getDescription() + " " +
                            p.getProductPrice());
        }

        entityManagerFactory.close();
    }
}