package controller.util;

import model.entity.Excursion;
import model.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Ticket ticket;
    private List<Excursion> excursions = new ArrayList<>();

    public boolean isEmpty() {
        return excursions.isEmpty();
    }

    public boolean add(Excursion excursion) {
        return excursions.add(excursion);
    }

    public boolean remove(Excursion e) {
        return excursions.remove(e);
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public List<Excursion> getExcursions() {
        return excursions;
    }
}
