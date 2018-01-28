package model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ticket implements Cloneable, Serializable, Entity {
    private final static long serialVersionUID = 1L;

    private int id;
    private long price;
    private int amountPassengers;
    private TicketClass type;
    private String person;
    private int place;
    private User user;
    private Tour tour;

    private Ticket(int id, String person, int place, long price,
                   int amountPassengers, TicketClass type,
                   User user, Tour tour) {
        this.id = id;
        this.person = person;
        this.place = place;
        this.price = price;
        this.type = type;
        this.user = user;
        this.tour = tour;
        this.amountPassengers = amountPassengers;
    }

    public int getId() {
        return id;
    }

    public String getPerson() {
        return person;
    }

    public int getPlace() {
        return place;
    }

    public long getPrice() {
        return price;
    }

    public int getAmountPassengers() {
        return amountPassengers;
    }

    public void setAmountPassengers(int amountPassengers) {
        this.amountPassengers = amountPassengers;
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

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public static Builder newTicket() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String person;
        private int place;
        private long price;
        private int amountPassengers;
        private TicketClass type;
        private User user;
        private Tour tour;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder person(String person) {
            this.person = person;
            return this;
        }

        public Builder place(int place) {
            this.place = place;
            return this;
        }

        public Builder price(long price) {
            this.price = price;
            return this;
        }

        public Builder amountPassengers(int amountPassengers) {
            this.amountPassengers = amountPassengers;
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
            return new Ticket(id, person, place,
                              price, amountPassengers,
                              type, user, tour);
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
                + "departure = " + person
                + ", arrival = " + place + "\n"
                + "type = " + type + ", price = " + price + "}";
    }
}
