package model.dao.mysql;

import model.dao.FactoryDAO;
import model.dao.interfaces.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FactoryMySQL extends FactoryDAO {

    @Override
    public ExcursionDAO excursionDAO(final Connection c) {
        return new ExcursionMySQL(c);
    }

    @Override
    public PortDAO portDAO(final Connection c) {
        return new PortMySQL(c);
    }

    @Override
    public RouteDAO routeDAO(final Connection c) {
        return new RouteMySQL(c);
    }

    @Override
    public UserDAO userDAO(final Connection c) {
        return new UserMySQL(c);
    }

    @Override
    public TourDAO tourDAO(final Connection c) {
        return new TourMySQL(c);
    }

    @Override
    public TicketDAO ticketDAO(final Connection c) {
        return new TicketMySQL(c);
    }

    @Override
    public ShipDAO shipDAO(final Connection c) {
        return new ShipMySQL(c);
    }

    @Deprecated
    public static Connection connect()  {
        ResourceBundle properties = ResourceBundle.getBundle("mysql");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    properties.getString("url"),
                    properties.getString("name"),
                    properties.getString("password")
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
