package model.dao.interfaces;

import model.entity.Ship;

import java.time.LocalDateTime;
import java.util.List;

public interface ShipDAO extends GenericDAO<Ship> {

    List<Ship> findFreeShip(LocalDateTime departure,
                            LocalDateTime arrival);


}
