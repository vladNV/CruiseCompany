package model.dao.mysql;

import model.dao.interfaces.ShipDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.EnumMapper;
import model.dao.mapper.Mapper;
import model.entity.Ship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static model.dao.queries.ShipSQL.*;

public class ShipMySQL implements ShipDAO {
    private final Connection connection;
    private int limit;
    private int offset;

    ShipMySQL(final Connection connection) {
        this.connection = connection;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
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
            Mapper<Ship> mapper = EntityMapper.mapperFactory(EnumMapper.ShipMapper);
            return EntityMapper.extractIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ship> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)){
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            Mapper<Ship> mapper = EntityMapper.mapperFactory(EnumMapper.ShipMapper);
            return EntityMapper.extractWhile(statement.executeQuery(), mapper);
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
