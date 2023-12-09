package user;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Mobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "mobile")
    private List<UserDetails> users;
}
