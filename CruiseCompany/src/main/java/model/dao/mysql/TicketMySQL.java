package model.dao.mysql;

import model.dao.interfaces.TicketDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.EnumMapper;
import model.dao.mapper.Mapper;
import model.entity.Ticket;

import java.sql.*;
import java.util.List;

public class TicketMySQL implements TicketDAO {
    private final Connection connection;
    private int offset;
    private int limit;

    private static final String INSERT =
            "insert into " +
                    "ticket(idtour, iduser, arrival, departure, price, type) " +
                    "values(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE =
            "update ticket set idtour = ?, iduser = ?, arrival = ?, " +
            "departure = ?, price = ?, type = ?";
    private static final String FIND =
            "select * from ticket where idticket = ?";
    private static final String TICKETS_TOUR =
            "select * from ticket where idtour = ? limit ?, ?";

    public TicketMySQL(final Connection connection) {
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
    public int insert(Ticket ticket) {
        try (PreparedStatement statement = connection
                .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, ticket.getTour().getId());
            statement.setInt(2, ticket.getUser().getId());
            statement.setTimestamp(3, Timestamp
                    .valueOf(ticket.getArrival()));
            statement.setTimestamp(4, Timestamp
                    .valueOf(ticket.getDeparture()));
            statement.setLong(5, ticket.getPrice());
            statement.setString(6, ticket.getType().name());
            return statement.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Ticket ticket) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setInt(1, ticket.getUser().getId());
            statement.setInt(2, ticket.getTour().getId());
            statement.setTimestamp(3, Timestamp.valueOf(ticket.getArrival()));
            statement.setTimestamp(4, Timestamp.valueOf(ticket.getDeparture()));
            statement.setLong(5, ticket.getPrice());
            statement.setString(6, String.valueOf(ticket.getType()));
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
    public Ticket findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND)){
            statement.setInt(1, id);
            Mapper<Ticket> mapper = EntityMapper.mapperFactory(EnumMapper.TicketMapper);
            return EntityMapper.extractIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public List<Ticket> ticketsForCruise(int tourId) {
        try (PreparedStatement statement = connection.prepareStatement(TICKETS_TOUR)) {
            statement.setInt(1, tourId);
            statement.setInt(2, offset);
            statement.setInt(3, limit);
            Mapper<Ticket> mapper = EntityMapper.mapperFactory(EnumMapper.TicketMapper);
            return EntityMapper.extractWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}