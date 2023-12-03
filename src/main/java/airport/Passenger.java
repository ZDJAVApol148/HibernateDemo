package airport;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PASSENGERS")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PASSENGER_ID")
    private long id;

    @Column(name = "PASSENGER_NAME", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL) // nazwa property z klasy Ticket wskazująca na obiekt Passenger
    private List<Ticket> tickets = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "PASSENGER_ADDRESS_CITY")),
            @AttributeOverride(name = "houseNumber", column = @Column(name = "PASSENGER_ADDRESS_HOUSE_NUMBER")),
            @AttributeOverride(name = "street", column = @Column(name = "PASSENGER_ADDRESS_STREET")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "PASSENGER_ADDRESS_ZIP_CODE"))
    })
    private Address address;

    public Passenger() {
    }

    public Passenger(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
