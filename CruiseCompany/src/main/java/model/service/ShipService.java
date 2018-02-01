package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.ShipDAO;
import model.dao.ConnectionPool;
import model.entity.Ship;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Ship service
 * @author  Nagaev Vladislav
 * @version 1.0
 */
public class ShipService {
    private final static Logger logger = Logger.getLogger(ShipService.class);

    private FactoryDAO factory;

    public ShipService() {
        factory = FactoryDAO.getDAOImpl(FactoryDAO.MYSQL);
    }

    /**
     * Selects ship by id
     * @param shipId identifier
     * @return ship
     */
    public Ship selectShip(int shipId) {
        logger.info("select ship: " + shipId);
        try (ShipDAO shipDAO = factory.shipDAO(ConnectionPool.pool().connect())){
            return shipDAO.findById(shipId);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Selects free ships in period departure-arrival
     * @param departure - time of departure
     * @param arrival - time of arrival
     * @return list with ships
     */
    public List<Ship> selectFreeShips(LocalDateTime departure, LocalDateTime arrival) {
        logger.info("select free ship from: " + departure.toString()
                + " to " + arrival.toString());
        try (ShipDAO shipDAO = factory.shipDAO(ConnectionPool.pool().connect())) {
            return shipDAO.findFreeShip(departure, arrival);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
}
