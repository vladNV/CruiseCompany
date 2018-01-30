package model.entity;

import java.io.Serializable;
import java.util.List;

public class Port implements Cloneable, Serializable, Entity {
    private final static long serialVersionUID = 1L;

    private int id;
    private String name;
    private String city;
    private String country;
    private List<Excursion> excursions;
    private List<Route> routes;

    private Port(int id, String name, String city,
                 String country, List<Excursion> excursions,
                 List<Route> routes) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.excursions = excursions;
        this.routes = routes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public List<Excursion> getExcursions() {
        return excursions;
    }

    public void setExcursions(List<Excursion> excursions) {
        this.excursions = excursions;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public static Builder newPort() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String name;
        private String city;
        private String country;
        private List<Excursion> excursions;
        private List<Route> routes;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder excursion(List<Excursion> excursions) {
            this.excursions = excursions;
            return this;
        }

        public Builder routes(List<Route> routes) {
            this.routes = routes;
            return this;
        }

        public Port build() {
            return new Port(id, name, city,
                    country, excursions, routes);
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
            Port p = (Port) obj;
            return id == p.id;
        }
        return false;
    }

    @Override
    public Port clone() throws CloneNotSupportedException {
        return (Port) super.clone();
    }

    @Override
    public String toString() {
        return "{id = " + id + ", name = " + name + "\n"
                + "country = " + country + ", city = " + city + "}";
    }
}
