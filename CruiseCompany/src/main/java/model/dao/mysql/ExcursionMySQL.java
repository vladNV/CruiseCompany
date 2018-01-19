package model.dao.mysql;

import model.dao.interfaces.ExcursionDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.EnumMapper;
import model.dao.mapper.ExcursionMapper;
import model.dao.mapper.Mapper;
import model.entity.Excursion;

import java.sql.*;
import java.util.List;

public class ExcursionMySQL implements ExcursionDAO {
    private final Connection connection;
    private int limit;
    private int offset;
    private static final String INSERT =
            "insert into excursion(idport, price, excursionname) values (?,?,?)";
    private static final String UPDATE =
            "update excursion set idport = ?, price = ?, " +
                    "excursionname = ? where idexcursion = ?";
    private static final String DELETE =
            "delete from excursion where idexcursion = ?";
    private static final String FIND =
            "select * from excursion where idexcursion = ?";
    private static final String FIND_ALL =
            "select * from excursion limit ?, ?";
    private static final String PORT_JOIN =
            "select * from excursion join port using(idport) limit ?, ?";

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

    public ExcursionMySQL(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Excursion excursion) {
        try (PreparedStatement statement = connection
                .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, excursion.getPort().getId());
            statement.setLong(2, excursion.getPrice());
            statement.setString(3, excursion.getName());
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
    public void update(Excursion excursion) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setInt(1, excursion.getPort().getId());
            statement.setLong(2, excursion.getPrice());
            statement.setString(3, excursion.getName());
            statement.setInt(4, excursion.getId());
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
    public Excursion findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND)) {
            statement.setInt(1, id);
            return ExcursionMapper.extractIf(statement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Excursion> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            return ExcursionMapper.extractWhile(statement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Excursion> joinWithPort() {
        try (PreparedStatement statement = connection.prepareStatement(PORT_JOIN)) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            Mapper<Excursion> mapper = EntityMapper.mapperFactory(EnumMapper.ExcursionMapper);
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
