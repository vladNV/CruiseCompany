package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.ExcursionDAO;
import model.dao.ConnectionPool;
import model.entity.Excursion;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * Excursion service
 */
public class ExcursionService {
    private final static Logger logger = Logger.getLogger(ExcursionService.class);

    private FactoryDAO factory;

    public ExcursionService() {
        factory = FactoryDAO.getDAOImpl(FactoryDAO.MYSQL);
    }

    /**
     * Show all excursions
     * @return list with excursions
     */

    public List<Excursion> showExcursions() {
        logger.info("show excursion");
        try (ExcursionDAO excursionDAO = factory
                .excursionDAO(ConnectionPool.pool().connect())){
            return excursionDAO.joinWithPort();
        } catch (Exception e) {
            logger.error("Excursion service exception", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Get excursion by id
     * @param id excursion
     * @return excursion entity
     */
    public Excursion getExcursion(int id) {
        logger.info("get excursion " + id);
        try (ExcursionDAO excursionDAO = factory
                .excursionDAO(ConnectionPool.pool().connect())){
            return excursionDAO.findById(id);
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

}
