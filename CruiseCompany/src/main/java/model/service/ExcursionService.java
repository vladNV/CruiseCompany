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
        ExcursionDAO excursionDAO = factory.excursionDAO(FactoryMySQL.connect());
        return excursionDAO.joinWithPort();

    }

}
