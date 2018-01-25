package model.dao.mysql;

import model.dao.interfaces.ShipDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.Mapper;
import model.dao.mapper.ShipMapper;
import model.entity.Ship;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static model.dao.queries.ShipSQL.*;

public class ShipMySQL implements ShipDAO {
    private final Connection connection;
    private final static Logger logger = Logger.getLogger(ShipMySQL.class);

    ShipMySQL(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Ship ship) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Ship ship) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Ship findById(int id) {
        logger.info("find by id");
        try (PreparedStatement statement = connection.prepareStatement(FIND)){
            statement.setInt(1, id);
            Mapper<Ship> mapper = new ShipMapper();
            return EntityMapper.extractNextIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ship> findAll() {
        logger.info("find by all");
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)){
            Mapper<Ship> mapper = new ShipMapper();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ship> findFreeShip(LocalDateTime departure,
                             LocalDateTime arrival) {
        logger.info("find free ship");
        try (PreparedStatement statement = connection.prepareStatement(FREE_SHIP)){
            statement.setTimestamp(1, Timestamp.valueOf(departure));
            statement.setTimestamp(2, Timestamp.valueOf(arrival));
            Mapper<Ship> mapper = new ShipMapper();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
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
