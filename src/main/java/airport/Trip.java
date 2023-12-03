package airport;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TRIPS")
public class Trip {

    public Trip(String name) {
        this.name = name;
    }

    public Trip() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRIP_ID")
    private long id;

    @Column(name = "TRIP_NAME")
    private String name;

    @ManyToMany(mappedBy = "trips", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

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
}
