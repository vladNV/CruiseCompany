package controller.util;

import model.entity.Excursion;
import model.entity.Ticket;

import java.util.HashSet;
import java.util.Set;

public class Cart {
    private Ticket ticket;
    private Set<Excursion> excursions = new HashSet<>();

    public boolean isEmpty() {
        return excursions.isEmpty();
    }

    public boolean add(Excursion e) {
        return excursions.add(e);
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

    public Set<Excursion> getExcursions() {
        return excursions;
    }

    public long getPrice(int amount) {
        long excursionPrice = 0;
        for (Excursion excursion : excursions) {
            excursionPrice += excursion.getPrice();
        }
        return amount * ticket.getPrice() + excursionPrice;
    }
}
