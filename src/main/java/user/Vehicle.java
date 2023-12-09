package user;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(mappedBy = "vehicles")
    private List<UserDetails> users;
}
