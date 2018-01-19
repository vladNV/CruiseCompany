package model.entity;

import java.io.Serializable;
import java.util.List;

public class Ship implements Cloneable, Serializable, Entity {
    private final static long serialVersionUID = 1L;

    private final int id;
    private final String name;
    private final int capacity;
    private List<Tour> tours;

    private Ship(int id, String name,
                 int capacity, List<Tour> tours) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.tours = tours;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Tour> getShips() {
        return tours;
    }

    public static Builder newShip() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String name;
        private int capacity;
        private Ship ship;
        private List<Tour> tours;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder tours(List<Tour> tours) {
            this.tours = tours;
            return this;
        }

        public Ship build() {
            return new Ship(id, name, capacity, tours);
        }
    }

    @Override
    public int hashCode() {
        return 31 * id + 37 * capacity + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj != null && obj.getClass() == getClass()) {
            Ship s = (Ship) obj;
            return id == s.id;
        }
        return false;
    }

    @Override
    public Ship clone() throws CloneNotSupportedException {
        return (Ship) super.clone();
    }

    @Override
    public String toString() {
        return "{id = " + id + ", name = " + name + ", capacity = " + capacity + "}";
    }
}
