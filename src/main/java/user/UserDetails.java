package user;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "mobile_id")
    private Mobile mobile;

    @ManyToMany
    private List<Vehicle> vehicles;
}
