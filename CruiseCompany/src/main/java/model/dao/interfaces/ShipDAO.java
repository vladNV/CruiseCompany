package model.dao.interfaces;

import model.entity.Ship;

import java.time.LocalDateTime;

public interface ShipDAO extends GenericDAO<Ship> {

    Ship findFreeShip(int id, LocalDateTime departure,
                      LocalDateTime arrival);


}
