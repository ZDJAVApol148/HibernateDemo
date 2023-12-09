package service;

import airport.Passenger;
import jakarta.persistence.EntityManager;

public class ProductService {

    private EntityManager entityManager;

    public ProductService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createPassenger(Passenger passenger) {
        entityManager.getTransaction().begin();

        entityManager.persist(passenger);

        entityManager.getTransaction().commit();
    }
}
