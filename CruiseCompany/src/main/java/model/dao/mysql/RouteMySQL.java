package model.dao.mysql;

import model.dao.interfaces.RouteDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.Mapper;
import model.dao.mapper.RouteMapper;
import model.entity.Route;

import java.sql.*;
import java.util.List;

import static model.dao.queries.RouteSQL.*;

public class RouteMySQL implements RouteDAO {
    private final Connection connection;


    RouteMySQL(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Route route) {
        try (PreparedStatement statement = connection
                .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, route.getTour().getId());
            statement.setTimestamp(2, Timestamp
                    .valueOf(route.getDeparture()));
            statement.setTimestamp(3, Timestamp
                    .valueOf(route.getArrival()));
            statement.setInt(4, route.getPort().getId());
            statement.setString(5, route.getName());
            return EntityMapper.getKey(statement.getGeneratedKeys());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Route route) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setInt(1, route.getTour().getId());
            statement.setTimestamp(2, Timestamp
                    .valueOf(route.getDeparture()));
            statement.setTimestamp(3, Timestamp
                    .valueOf(route.getArrival()));
            statement.setInt(4, route.getPort().getId());
            statement.setString(5, route.getName());
            statement.setInt(6, route.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Route findById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Route> findAll() {
        throw new UnsupportedOperationException();
    }


    @Override
    public List<Route> routesOfCruise(int tourId) {
        try (PreparedStatement statement = connection
                .prepareStatement(TOUR_ROUTES)) {
            statement.setInt(1, tourId);
            Mapper<Route> mapper = new RouteMapper();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

}
