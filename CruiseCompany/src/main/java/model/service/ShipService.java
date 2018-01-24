package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.ShipDAO;
import model.dao.mysql.ConnectionPool;
import model.entity.Ship;

import java.util.List;

public class ShipService {
    private FactoryDAO factory;

    public ShipService() {
        factory = FactoryDAO.getDAOImpl(FactoryDAO.MYSQL);
    }

    public Ship selectShip(int shipId) {
        try (ShipDAO shipDAO = factory.shipDAO(ConnectionPool.pool().connect())){
            return shipDAO.findById(shipId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ship> selectShips() {
        try (ShipDAO shipDAO = factory.shipDAO(ConnectionPool.pool().connect())) {
            return shipDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
