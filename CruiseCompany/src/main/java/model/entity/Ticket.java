package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ticket implements Cloneable, Serializable, Entity {
    private final static long serialVersionUID = 1L;

    private int id;
    private long price;
    private int amountPassengers;
    private TicketClass type;
    private String person;
    private int place;
    private List<TicketBonus> bonus;
    private User user;
    private Tour tour;

    private Ticket(int id, String person, int place, long price,
                   int amountPassengers, TicketClass type,
                   User user, Tour tour, List<TicketBonus> bonus) {
        this.id = id;
        this.person = person;
        this.place = place;
        this.price = price;
        this.type = type;
        this.user = user;
        this.tour = tour;
        this.bonus = bonus;
        this.amountPassengers = amountPassengers;
    }

    public List<TicketBonus> getBonus() {
        return bonus;
    }

    public String getBonusRow() {
        StringBuilder sb = new StringBuilder();
        for (TicketBonus bonus : this.bonus) {
            sb.append(bonus.toString()).append(",");
        }
        if (sb.toString().isEmpty()) return "";
        return sb.toString().substring(0, sb.length() - 1);
    }

    public int getId() {
        return id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
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
        private List<TicketBonus> bonus;
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

        public Builder bonus(String bonus) {
            List<TicketBonus> bonusList = new ArrayList<>();
            if (bonus != null && !bonus.isEmpty()) {
                List<String> list = Arrays.asList(bonus.split(","));
                list.forEach(b -> bonusList.add(TicketBonus.valueOf(b.toUpperCase())));
            }
            this.bonus = bonusList;
            return this;
        }

        public Builder bonus(List<TicketBonus> bonus) {
            if (bonus == null) bonus = new ArrayList<>();
            this.bonus = bonus;
            return this;
        }

        public Ticket build() {
            return new Ticket(id, person, place,
                              price, amountPassengers,
                              type, user, tour, bonus);
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
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", amountPassengers=" + amountPassengers +
                ", type=" + type +
                ", person='" + person + '\'' +
                ", place=" + place +
                ", bonus='" + bonus + '\'' +
                '}';
    }
}
