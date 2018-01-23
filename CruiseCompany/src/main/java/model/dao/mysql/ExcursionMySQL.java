package model.dao.mysql;

import static model.dao.queries.ExcursionSQL.*;

import model.dao.interfaces.ExcursionDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.ExcursionMapper;
import model.dao.mapper.Mapper;
import model.dao.mapper.join.ExcursionPortJoin;
import model.entity.Excursion;
import model.entity.Ticket;

import java.sql.*;
import java.util.List;
import java.util.Set;

public class ExcursionMySQL implements ExcursionDAO {
    private final Connection connection;
    private int limit;
    private int offset;

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

    ExcursionMySQL(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Excursion excursion) {
        try (PreparedStatement statement = connection
                .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, excursion.getPort().getId());
            statement.setLong(2, excursion.getPrice());
            statement.setString(3, excursion.getName());
            return EntityMapper.getKey(statement.getGeneratedKeys());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Excursion excursion) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setInt(1, excursion.getPort().getId());
            statement.setLong(2, excursion.getPrice());
            statement.setString(3, excursion.getName());
            statement.setInt(4, excursion.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Excursion findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND)) {
            statement.setInt(1, id);
            Mapper<Excursion> mapper = new ExcursionMapper();
            return EntityMapper.extractNextIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Excursion> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            Mapper<Excursion> mapper = new ExcursionMapper();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Excursion> joinWithPort() {
        try (PreparedStatement statement = connection.prepareStatement(PORT_JOIN)) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            Mapper<Excursion> mapper = new ExcursionPortJoin();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Excursion> cruiseExcursion(int tourId) {
        try (PreparedStatement statement = connection.prepareStatement(EXCURSION_PORT)){
            statement.setInt(1, tourId);
            Mapper<Excursion> mapper = new ExcursionMapper();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserExcursion(Ticket ticket, Set<Excursion> excursions) {
        try (PreparedStatement statement = connection.prepareStatement(USER_EXCURSION)){
            for (Excursion e : excursions) {
                statement.setInt(1, e.getId());
                statement.setInt(2, ticket.getId());
                statement.addBatch();
            }
            statement.executeBatch();
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
