package model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Tour implements Cloneable, Serializable, Entity {
    private final static long serialVersionUID = 1L;

    private int id;
    private String name;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private String region;
    private CruiseDuration duration;
    private List<Route> routes;
    private List<Ticket> tickets;
    private Ship ship;

    private Tour(int id, String name, LocalDateTime departure,
                LocalDateTime arrival, List<Route> routes,
                 List<Ticket> tickets, Ship ship,
                 String region) {
        this.id = id;
        this.name = name;
        this.departure = departure;
        this.arrival = arrival;
        this.routes = routes;
        this.tickets = tickets;
        this.ship = ship;
        this.region = region;
        duration = new CruiseDuration(departure, arrival);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public CruiseDuration getDuration() {
        return duration;
    }

    public String getRegion() {
        return region;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public static Builder newTour() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String name;
        private LocalDateTime departure;
        private LocalDateTime arrival;
        private String region;
        private List<Route> routes;
        private List<Ticket> tickets;
        private Ship ship;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder departure(LocalDateTime departure) {
            this.departure = departure;
            return this;
        }

        public Builder arrival(LocalDateTime arrival) {
            this.arrival = arrival;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }

        public Builder routes(List<Route> routes) {
            this.routes = routes;
            return this;
        }

        public Builder tickets(List<Ticket> tickets) {
            this.tickets = tickets;
            return this;
        }

        public Builder ship(Ship ship) {
            this.ship = ship;
            return this;
        }

        public Tour build() {
            return new Tour(id, name, departure, arrival,
                            routes, tickets, ship, region);
        }
    }

    @Override
    public int hashCode() {
        return 31 * id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj != null && obj.getClass() == getClass()) {
            Tour t = (Tour) obj;
            return id == t.id;
        }
        return false;
    }

    @Override
    public Tour clone() throws CloneNotSupportedException {
        return (Tour) super.clone();
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departure=" + departure.toString() +
                ", arrival=" + arrival.toString() +
                '}';
    }
}
