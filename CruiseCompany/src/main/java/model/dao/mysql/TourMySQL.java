package model.dao.mysql;

import model.dao.interfaces.TourDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.Mapper;
import model.dao.mapper.TourMapper;
import model.dao.mapper.join.TourShipJoin;
import model.entity.Tour;

import java.sql.*;
import java.util.List;

import static model.dao.queries.TourSQL.*;

public class TourMySQL implements TourDAO {
    private final Connection connection;
    private int offset;
    private int limit;

    TourMySQL(final Connection connection) {
        this.connection = connection;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public int insert(Tour tour) {
        try (PreparedStatement statement = connection
                .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, tour.getName());
            statement.setInt(2, tour.getShip().getId());
            statement.setTimestamp(3, Timestamp.valueOf(tour.getArrival()));
            statement.setTimestamp(4, Timestamp.valueOf(tour.getDeparture()));
            statement.setString(5, tour.getRegion());
            return statement.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException roll) {
                roll.printStackTrace();
                throw new RuntimeException(roll);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Tour tour) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, tour.getName());
            statement.setInt(2, tour.getShip().getId());
            statement.setTimestamp(3, Timestamp.valueOf(tour.getArrival()));
            statement.setTimestamp(4, Timestamp.valueOf(tour.getDeparture()));
            statement.setString(5, tour.getRegion());
            statement.setInt(6, tour.getId());
            statement.execute();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException roll) {
                roll.printStackTrace();
                throw new RuntimeException(roll);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tour findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND)){
            statement.setInt(1, id);
            Mapper<Tour> mapper = new TourMapper();
            return EntityMapper.extractNextIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException roll) {
                roll.printStackTrace();
                throw new RuntimeException(roll);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tour> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tour> joinWithShip() {
        try (PreparedStatement statement = connection
                .prepareStatement(TOUR_WITH_SHIP)){
            Mapper<Tour> mapper = new TourShipJoin();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException roll) {
                roll.printStackTrace();
                throw new RuntimeException(roll);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tour> search(String region) {
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_FOR_REGION)){
            statement.setString(1, region);
            Mapper<Tour> mapper = new TourShipJoin();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tour findTourWithShip(int tourId) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_TOUR_SHIP)){
            statement.setInt(1, tourId);
            Mapper<Tour> mapper = new TourShipJoin();
            return EntityMapper.extractNextIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException roll) {
                roll.printStackTrace();
                throw new RuntimeException(roll);
            }
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