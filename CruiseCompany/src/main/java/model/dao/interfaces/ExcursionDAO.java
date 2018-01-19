package model.dao.interfaces;

import model.entity.Excursion;

import java.util.List;

public interface ExcursionDAO extends GenericDAO<Excursion> {

    List<Excursion> joinWithPort();
    List<Excursion> cruiseExcursion(int tourId);

}
