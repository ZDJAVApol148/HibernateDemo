package airport;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class Program {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("my-persistence-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Passenger john = new Passenger("John Smith");
        Address johnAddress = new Address();
        johnAddress.setCity("Warszawa");
        johnAddress.setStreet("Puławska");
        johnAddress.setZipCode("00-000");
        johnAddress.setHouseNumber("134");

        john.setAddress(johnAddress);

        Passenger eve = new Passenger("Eve Doe");
        Address eveAddress = new Address();
        eveAddress.setCity("Wrocław");
        eveAddress.setStreet("Długa");
        eveAddress.setZipCode("88-988");
        eveAddress.setHouseNumber("11");

        eve.setAddress(eveAddress);

        Ticket ticket1 = new Ticket();
        ticket1.setNumber("1234");
        ticket1.setPassenger(john);
        john.addTicket(ticket1);

        Ticket ticket2 = new Ticket();
        ticket2.setNumber("2345");
        ticket2.setPassenger(john);
        john.addTicket(ticket2);

        Ticket ticket3 = new Ticket();
        ticket3.setNumber("56678");
        ticket3.setPassenger(eve);
        eve.addTicket(ticket3);

        Ticket ticket4 = new Ticket();
        ticket4.setNumber("9810");
        ticket4.setPassenger(eve);
        eve.addTicket(ticket4);

        Trip trip1 = new Trip("Euro trip");
        Trip trip2 = new Trip("Asia trip");
        Trip trip3 = new Trip("US trip");
        Trip trip4 = new Trip("Transatlantic trip");

        trip1.addTicket(ticket1);
        trip1.addTicket(ticket2);
        trip1.addTicket(ticket3);

        ticket1.addTrip(trip1);
        ticket2.addTrip(trip1);
        ticket3.addTrip(trip1);

        trip2.addTicket(ticket2);
        trip2.addTicket(ticket3);
        trip2.addTicket(ticket4);

        ticket2.addTrip(trip2);
        ticket3.addTrip(trip2);
        ticket4.addTrip(trip2);

        trip3.addTicket(ticket3);
        trip3.addTicket(ticket4);
        trip3.addTicket(ticket1);

        ticket3.addTrip(trip3);
        ticket4.addTrip(trip3);
        ticket1.addTrip(trip3);

        trip4.addTicket(ticket4);
        trip4.addTicket(ticket1);
        trip4.addTicket(ticket2);

        ticket4.addTrip(trip4);
        ticket1.addTrip(trip4);
        ticket2.addTrip(trip4);

        entityManager.persist(john);
        entityManager.persist(eve);
//        entityManager.persist(ticket1);
//        entityManager.persist(ticket2);
//        entityManager.persist(ticket3);
//        entityManager.persist(ticket4);
//        entityManager.persist(trip1);
//        entityManager.persist(trip2);
//        entityManager.persist(trip3);
//        entityManager.persist(trip4);

        entityManager.getTransaction().commit();

        // FROM - pobiera wszystkie zapisane obiekty do pamięci
        Query query = entityManager.createQuery("FROM Passenger");
        List<Passenger> result = query.getResultList();

        System.out.println("*********************");
        System.out.println("FROM");
        for (Passenger p : result) {
            System.out.println(p);
        }
        System.out.println("*********************");

        query = entityManager.createQuery("SELECT p.name FROM Passenger p");
        List<String> result2 = query.getResultList();

        System.out.println("*********************");
        System.out.println("SELECT FROM");
        for (String p : result2) {
            System.out.println(p);
        }
        System.out.println("*********************");

        query = entityManager.createQuery("FROM Passenger p ORDER BY p.id DESC");
        result = query.getResultList();

        System.out.println("*********************");
        System.out.println("ORDER BY");
        for (Passenger p : result) {
            System.out.println(p);
        }
        System.out.println("*********************");

        query = entityManager.createQuery("FROM Passenger p WHERE p.id = :passenger_id");
        query.setParameter("passenger_id", 2);
        result = query.getResultList();

        System.out.println("*********************");
        System.out.println("WHERE + parameter");
        for (Passenger p : result) {
            System.out.println(p);
        }
        System.out.println("*********************");

        entityManager.getTransaction().begin();

        query = entityManager.createQuery("UPDATE Passenger SET name = :name WHERE id = :passenger_id");
        query.setParameter("passenger_id", 2);
        query.setParameter("name", "Dorothy Wizard");
        int count = query.executeUpdate();

        entityManager.getTransaction().commit();

        // entityManager.refresh(entityManager.find(Passenger.class, 2));

        query = entityManager.createQuery("FROM Passenger");
        result = query.getResultList();

        System.out.println("*********************");
        System.out.println(count);
        System.out.println("After UPDATE");
        for (Passenger p : result) {
            System.out.println(p);
        }
        System.out.println("*********************");

        /*entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Passenger.class, 1));

        entityManager.getTransaction().commit();*/

        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();

        query = entityManager.createQuery("FROM Passenger");
        result = query.getResultList();

        System.out.println("*********************");
        System.out.println(count);
        System.out.println("After UPDATE");
        for (Passenger p : result) {
            System.out.println(p);
        }
        System.out.println("*********************");

        entityManager.find(Passenger.class, 2);

        entityManager.close();

        entityManagerFactory.close();
    }
}
