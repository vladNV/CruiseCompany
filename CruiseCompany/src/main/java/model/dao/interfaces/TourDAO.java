package model.dao.interfaces;

import model.entity.Ticket;
import model.entity.Tour;

import java.util.List;

public interface TourDAO extends GenericDAO<Tour> {

    List<Tour> joinWithShip();
    Tour findTourWithShip(int tourId);

}
