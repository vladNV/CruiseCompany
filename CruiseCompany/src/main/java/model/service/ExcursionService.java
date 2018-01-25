package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.ExcursionDAO;
import model.dao.mysql.ConnectionPool;
import model.entity.Excursion;

import java.util.List;

import org.apache.log4j.Logger;

public class ExcursionService {
    private final static Logger logger = Logger.getLogger(ExcursionService.class);

    private FactoryDAO factory;

    public ExcursionService() {
        factory = FactoryDAO.getDAOImpl(FactoryDAO.MYSQL);
    }

    public List<Excursion> showExcursions() {
        logger.info("show excursion");
        try (ExcursionDAO excursionDAO = factory.excursionDAO(ConnectionPool.pool().connect())){
            return excursionDAO.joinWithPort();
        } catch (Exception e) {
            logger.error("Excursion service exception", e);
            throw new RuntimeException(e);
        }
    }

    public List<Excursion> showCruiseExcursion(int tourId) {
        logger.info("show cruise excursion " + tourId);
        try (ExcursionDAO excursionDAO = factory
                .excursionDAO(ConnectionPool.pool().connect())){
            return excursionDAO.cruiseExcursion(tourId);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    public Excursion getExcursion(int id) {
        logger.info("get excursion " + id);
        try (ExcursionDAO excursionDAO = factory.excursionDAO(ConnectionPool.pool().connect())){
            return excursionDAO.findById(id);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

}
