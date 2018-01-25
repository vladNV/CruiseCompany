package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.PortDAO;
import model.dao.mysql.ConnectionPool;
import model.entity.Port;
import org.apache.log4j.Logger;

import java.util.List;

public class PortService {
    private final static Logger logger = Logger.getLogger(PortService.class);
    private FactoryDAO factory;

    public PortService() {
        factory = FactoryDAO.getDAOImpl(FactoryDAO.MYSQL);
    }

    public List<Port> selectPorts() {
        logger.info("select ports");
        try (PortDAO portDAO = factory.portDAO(ConnectionPool.pool().connect())){
            return portDAO.findAll();
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

}
