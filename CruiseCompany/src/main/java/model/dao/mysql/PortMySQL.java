package model.dao.mysql;

import model.dao.interfaces.PortDAO;
import model.dao.mapper.PortMapper;
import model.entity.Port;

import java.sql.*;
import java.util.List;

public class PortMySQL implements PortDAO {
    private final Connection connection;
    private int limit;
    private int offset;

    private static final String INSERT =
            "insert into port(portname, city, country) values (?, ?, ?)";
    private static final String UPDATE =
            "update port set portname = ?, city = ?, country = ? where idport = ?";
    private static final String DELETE =
            "delete from port where idport = ?";
    private static final String FIND =
            "select * from port where idport = ?";
    private static final String FIND_ALL =
            "select * from port limit ?, ?";

    public PortMySQL(final Connection connection) {
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
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
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
            return PortMapper.extractIf(statement.executeQuery());
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
            return PortMapper.extractWhile(statement.executeQuery());
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
