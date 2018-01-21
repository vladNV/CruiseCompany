package model.dao.mysql;

import model.dao.interfaces.TicketDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.EnumMapper;
import model.dao.mapper.Mapper;
import model.entity.Ticket;
import model.exceptions.TicketPaidException;
import model.util.AggregateOperation;
import model.util.TicketClass;

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
    private static final String     TICKETS_TOUR =
            "select * from ticket where idtour = ? limit ?, ?";
    private static final String QUANTITY_TICKET =
            "select count(*) as amount, departure, arrival, price, type " +
            "from ticket where idtour = ? and iduser is null " +
            "group by ticket.type";
    private static final String FIND_BY_TYPE =
            "select * from ticket where type = ? and idtour = ? limit 1";
    private static final String UPDATE_USER_TICKET =
            "update ticket set iduser = ?, amount_passengers = ? " +
            "where idticket = ? and iduser is null";

    TicketMySQL(final Connection connection) {
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
            statement.execute();
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
    public List<AggregateOperation<Integer, Ticket>> ticketForCategory(int tourId) {
        try (PreparedStatement statement = connection.prepareStatement(QUANTITY_TICKET)){
            statement.setInt(1, tourId);
            Mapper<AggregateOperation<Integer, Ticket>> mapper =
                    EntityMapper.mapperFactory(EnumMapper.TicketAmountMapper);
            return EntityMapper.extractWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    @Override
    public Ticket findTicketByType(TicketClass type, int tourId) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_TYPE)) {
            statement.setString(1, String.valueOf(type));
            statement.setInt(2, tourId);
            Mapper<Ticket> mapper = EntityMapper.
                    mapperFactory(EnumMapper.TicketWithoutTourMapper);
            return EntityMapper.extractIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTicket(Ticket ticket, int userId) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_TICKET)){
            statement.setInt(1, userId);
            statement.setInt(2, ticket.getAmountPassengers());
            statement.setInt(3, ticket.getId());
            int update = statement.executeUpdate();
            if(update == 0) throw new TicketPaidException();
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
}
