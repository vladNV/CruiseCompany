package model.dao.interfaces;

import model.entity.Tour;

import java.util.List;

public interface TourDAO extends GenericDAO<Tour> {

    List<Tour> joinWithShip(int offset);
    List<Tour> search(String region, int offset);
    Tour findTourWithShip(int tourId);
    int amount();
    int amount(String name);

}
