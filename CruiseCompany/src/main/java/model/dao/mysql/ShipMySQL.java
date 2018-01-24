package model.dao.mysql;

import model.dao.interfaces.ShipDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.Mapper;
import model.dao.mapper.ShipMapper;
import model.entity.Ship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static model.dao.queries.ShipSQL.*;

public class ShipMySQL implements ShipDAO {
    private final Connection connection;

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
        try (PreparedStatement statement = connection.prepareStatement(FIND)){
            statement.setInt(1, id);
            Mapper<Ship> mapper = new ShipMapper();
            return EntityMapper.extractIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ship> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)){
            Mapper<Ship> mapper = new ShipMapper();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ship findFreeShip(int id, LocalDateTime departure,
                             LocalDateTime arrival) {
        return null;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
