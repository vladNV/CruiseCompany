package model.dao.mysql;

import model.dao.interfaces.TourDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.Mapper;
import model.dao.mapper.TourMapper;
import model.dao.mapper.join.TourShipJoin;
import model.entity.Tour;
import model.service.TourService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

import static model.dao.queries.TourSQL.*;

public class TourMySQL implements TourDAO {
    private final Connection connection;
    private final static Logger logger = Logger.getLogger(TourMySQL.class);

    TourMySQL(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Tour tour) {
        logger.info("insert");
        try (PreparedStatement statement = connection
                .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, tour.getName());
            statement.setInt(2, tour.getShip().getId());
            statement.setTimestamp(3, Timestamp.valueOf(tour.getArrival()));
            statement.setTimestamp(4, Timestamp.valueOf(tour.getDeparture()));
            statement.setString(5, tour.getRegion());
            statement.execute();
            return EntityMapper.getKey(statement.getGeneratedKeys());
        } catch (SQLException e) {
            try {
                logger.info("connection insert rollback");
                connection.rollback();
            } catch (SQLException roll) {
                logger.error("rollback error ", e);
                throw new RuntimeException(roll);
            }
            logger.error(e);
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
                logger.info("connection update rollback");
                connection.rollback();
            } catch (SQLException roll) {
                logger.error("rollback error ", e);
                throw new RuntimeException(roll);
            }
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tour findById(int id) {
        logger.info("find by id");
        try (PreparedStatement statement = connection.prepareStatement(FIND)){
            statement.setInt(1, id);
            Mapper<Tour> mapper = new TourMapper();
            return EntityMapper.extractNextIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            try {
                logger.info("connection find by id rollback");
                connection.rollback();
            } catch (SQLException roll) {
                logger.error("rollback error ", e);
                throw new RuntimeException(roll);
            }
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tour> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tour> joinWithShip(int offset) {
        try (PreparedStatement statement = connection
                .prepareStatement(TOUR_WITH_SHIP)){
            statement.setInt(2, TourService.LIMIT_TOUR);
            statement.setInt(1, offset);
            Mapper<Tour> mapper = new TourShipJoin();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException roll) {
                logger.error("rollback error ", e);
                throw new RuntimeException(roll);
            }
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tour> search(String region, int offset) {
        logger.info("search");
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_FOR_REGION)){
            statement.setString(1, region + "%");
            statement.setInt(3, TourService.LIMIT_TOUR);
            statement.setInt(2, offset);
            Mapper<Tour> mapper = new TourShipJoin();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tour findTourWithShip(int tourId) {
        logger.info("find tour with ship");
        try (PreparedStatement statement = connection.prepareStatement(FIND_TOUR_SHIP)){
            statement.setInt(1, tourId);
            Mapper<Tour> mapper = new TourShipJoin();
            return EntityMapper.extractNextIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException roll) {
                logger.error("rollback error ", e);
                throw new RuntimeException(roll);
            }
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int amount() {
        logger.info("amount");
        try (PreparedStatement statement = connection.prepareStatement(COUNT)){
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int amount(String name) {
        logger.info("amount");
        try (PreparedStatement statement = connection.prepareStatement(COUNT_WITH_PARAM)){
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            logger.error(e);
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