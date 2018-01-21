package model.service;

import model.dao.FactoryDAO;
import model.dao.interfaces.ExcursionDAO;
import model.dao.mysql.FactoryMySQL;
import model.entity.Excursion;

import java.util.List;

public class ExcursionService {
    private FactoryDAO factory;

    public ExcursionService(FactoryDAO factory) {
        this.factory = factory;
    }

    public List<Excursion> showExcursions() {
        try (ExcursionDAO excursionDAO = factory.excursionDAO(FactoryMySQL.connect());){
            return excursionDAO.joinWithPort();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Excursion> showCruiseExcursion(int tourId) {
        try (ExcursionDAO excursionDAO = factory.excursionDAO(FactoryMySQL.connect());){
            return excursionDAO.cruiseExcursion(tourId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Excursion getExcursion(int id) {
        try (ExcursionDAO excursionDAO = factory.excursionDAO(FactoryMySQL.connect());){
            return excursionDAO.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
