package model.dao.mysql;

import model.dao.interfaces.PortDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.EnumMapper;
import model.dao.mapper.Mapper;
import model.entity.Port;

import java.sql.*;
import java.util.List;

import static model.dao.queries.PortSQL.*;

public class PortMySQL implements PortDAO {
    private final Connection connection;
    private int limit;
    private int offset;

    PortMySQL(final Connection connection) {
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
    public int insert(Port port) {
        try (PreparedStatement statement = connection
                .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, port.getName());
            statement.setString(2, port.getCity());
            statement.setString(3, port.getCountry());
            return EntityMapper.getKey(statement.getGeneratedKeys());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Port port) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, port.getName());
            statement.setString(2, port.getCity());
            statement.setString(3, port.getCountry());
            statement.setInt(4, port.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Port findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND)){
            statement.setInt(1, id);
            Mapper<Port> mapper = EntityMapper.mapperFactory(EnumMapper.PortMapper);
            return EntityMapper.extractIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Port> findAll() {
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_ALL)){
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            Mapper<Port> mapper = EntityMapper.mapperFactory(EnumMapper.PortMapper);
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
