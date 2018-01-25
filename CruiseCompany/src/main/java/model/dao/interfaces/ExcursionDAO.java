package model.dao.interfaces;

import model.entity.Excursion;
import model.entity.Ticket;

import java.util.List;
import java.util.Set;

public interface ExcursionDAO extends GenericDAO<Excursion> {

    List<Excursion> joinWithPort();
    List<Excursion> cruiseExcursion(int tourId);
    void updateUserExcursion(Ticket ticket, Set<Excursion> excursions);

}
