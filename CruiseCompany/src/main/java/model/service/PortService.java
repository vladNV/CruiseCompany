package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.PortDAO;
import model.dao.mysql.ConnectionPool;
import model.entity.Port;

import java.util.List;

public class PortService {
    private FactoryDAO factory;

    public PortService() {
        factory = FactoryDAO.getDAOImpl(FactoryDAO.MYSQL);
    }

    public List<Port> selectPorts() {
        try (PortDAO portDAO = factory.portDAO(ConnectionPool.pool().connect())){
            return portDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
