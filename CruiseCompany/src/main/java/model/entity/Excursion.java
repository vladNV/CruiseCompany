package model.entity;

import java.io.Serializable;

public class Excursion implements Cloneable, Serializable, Entity {
    private final static long serialVersionUID = 1L;

    private int id;
    private String name;
    private long price;
    private Port port;

    private Excursion(int id, String name,
                      long price, Port port) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.port = port;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public Port getPort() {
        return port;
    }

    public static Builder newExcursion() {
        return new Builder();
    }

    public static class Builder implements model.util.Builder<Excursion> {
        private int id;
        private String name;
        private long price;
        private Port port;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(long price) {
            this.price = price;
            return this;
        }

        public Builder port(Port port) {
            this.port = port;
            return this;
        }

        @Override
        public Excursion build() {
            return new Excursion(id, name, price, port);
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
            Excursion e = (Excursion) obj;
            return id == e.id;
        }
        return false;
    }

    @Override
    public Excursion clone() throws CloneNotSupportedException {
        return (Excursion) super.clone();
    }

    @Override
    public String toString() {
        return "{id = " + id + ", name = " + name + ", price = " + price + "}";
    }
}
