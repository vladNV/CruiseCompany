package model.entity;

import model.entity.Port;
import model.entity.Ship;
import model.entity.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourBuilder {
    private Tour tour;
    private List<Port> ports = new ArrayList<>();
    private List<Ship> ships = new ArrayList<>();

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }
}
