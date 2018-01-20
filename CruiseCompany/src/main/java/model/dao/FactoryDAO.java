package model.dao;

import model.dao.interfaces.*;
import model.dao.mysql.FactoryMySQL;

import java.sql.Connection;

public abstract class FactoryDAO {
    public static final int MYSQL = 1;
    public static final int ORACLE = 2;

    public abstract ExcursionDAO excursionDAO(Connection c);
    public abstract PortDAO portDAO(Connection c);
    public abstract RouteDAO routeDAO(Connection c);
    public abstract UserDAO userDAO(Connection c);
    public abstract TourDAO tourDAO(Connection c);
    public abstract TicketDAO ticketDAO(Connection c);
    public abstract ShipDAO shipDAO(Connection c);

    public static FactoryDAO getDAOImpl(int key) {
        switch (key) {
            case MYSQL:
                return new FactoryMySQL();
            default:
                throw new IllegalArgumentException
                        ("such implementation does not exist!");
        }
    }

}
