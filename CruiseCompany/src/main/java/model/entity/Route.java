package model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Route implements Cloneable, Serializable, Entity {
    private final static long serialVersionUID = 1L;

    private int id;
    private String name;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private Port port;
    private Tour tour;

    private Route(int id, String name, LocalDateTime departure,
          LocalDateTime arrival, Port port, Tour tour) {
        this.id = id;
        this.name = name;
        this.departure = departure;
        this.arrival = arrival;
        this.tour = tour;
        this.port = port;
    }

    public int getId() {
        return id;
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

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public Tour getTour() {
        return tour;
    }

    public static Builder newRoute() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String name;
        private LocalDateTime departure;
        private LocalDateTime arrival;
        private Tour tour;
        private Port port;

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

        public Builder port(Port port) {
            this.port = port;
            return this;
        }

        public Builder tour(Tour tour) {
            this.tour = tour;
            return this;
        }

        public Route build() {
            return new Route(id, name, departure,
                             arrival, port, tour);
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
            Route r = (Route) obj;
            return id == r.id;
        }
        return false;
    }

    @Override
    public Route clone() throws CloneNotSupportedException {
        return (Route) super.clone();
    }

    @Override
    public String toString() {
        return "{id = " + id + ", name = " + name + "\n"
                + "departure = " + departure.toString()
                + ", arrival = " + arrival.toString() + "}";
    }
}
