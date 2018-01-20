package model.dao.interfaces;

import model.entity.Excursion;
import model.entity.Ticket;
import model.entity.User;

import java.util.List;

public interface ExcursionDAO extends GenericDAO<Excursion> {

    List<Excursion> joinWithPort();
    List<Excursion> cruiseExcursion(int tourId);
    void updateUserExcursion(Ticket ticket, List<Excursion> excursions);

}
