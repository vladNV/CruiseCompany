package model.entity;

import model.util.TicketClass;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ticket implements Cloneable, Serializable, Entity {
    private final static long serialVersionUID = 1L;

    private int id;
    private LocalDateTime arrival;
    private LocalDateTime departure;
    private long price;
    private TicketClass type;
    private CruiseDuration duration;
    private User user;
    private Tour tour;

    private Ticket(int id, LocalDateTime arrival, LocalDateTime departure,
                   long price, TicketClass type,
                   User user, Tour tour) {
        this.id = id;
        this.arrival = arrival;
        this.departure = departure;
        this.price = price;
        this.type = type;
        this.user = user;
        this.tour = tour;
        duration = new CruiseDuration(departure, arrival);
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public CruiseDuration getDuration() {
        return duration;
    }

    public long getPrice() {
        return price;
    }

    public TicketClass getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public Tour getTour() {
        return tour;
    }

    public static Builder newTicket() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private LocalDateTime arrival;
        private LocalDateTime departure;
        private long price;
        private TicketClass type;
        private User user;
        private Tour tour;

        public Builder id(int id) {
            this.id = id;
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

        public Builder price(long price) {
            this.price = price;
            return this;
        }

        public Builder type(TicketClass type) {
            this.type = type;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder tour(Tour tour) {
            this.tour = tour;
            return this;
        }

        public Ticket build() {
            return new Ticket(id, arrival, departure,
                              price, type, user, tour);
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
            Ticket t = (Ticket) obj;
            return id == t.id;
        }
        return false;
    }

    @Override
    protected Ticket clone() throws CloneNotSupportedException {
        return (Ticket) super.clone();
    }

    @Override
    public String toString() {
        return "{id = " + id + "\n"
                + "departure = " + departure.toString()
                + ", arrival = " + arrival.toString() + "\n"
                + "type = " + type + ", price = " + price + "}";
    }
}
