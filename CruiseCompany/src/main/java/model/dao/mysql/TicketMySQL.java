package model.dao.mysql;

import model.dao.interfaces.TicketDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.EnumMapper;
import model.dao.mapper.Mapper;
import model.entity.Ticket;
import model.exceptions.ServiceException;
import model.dao.mapper.AggregateOperation;
import model.entity.TicketClass;

import static model.dao.queries.TicketSQL.*;
import java.sql.*;
import java.util.List;

public class TicketMySQL implements TicketDAO {
    private final Connection connection;
    private int offset;
    private int limit;

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
    public List<Ticket> userTickets(int userId) {
        try (PreparedStatement statement = connection.prepareStatement(USER_TICKETS)){
            statement.setInt(1, userId);
            Mapper<Ticket> mapper = EntityMapper
                    .mapperFactory(EnumMapper.TicketWithoutTourMapper);
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
                    mapperFactory(EnumMapper.TicketMapper);
            return EntityMapper.extractIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTicket(Ticket ticket, int userId) throws ServiceException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_TICKET)){
            statement.setInt(1, userId);
            statement.setInt(2, ticket.getAmountPassengers());
            statement.setInt(3, ticket.getId());
            int update = statement.executeUpdate();
            if(update == 0) throw new ServiceException("ticket.was.bought");
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

    @Override
    public void existTicket(int userId, int tourId)
            throws ServiceException {
        try (PreparedStatement statement = connection.prepareStatement(EXIST_TICKET)) {
            statement.setInt(1, tourId);
            statement.setInt(2, userId);
            boolean result = EntityMapper.exist(statement.executeQuery());
            if (result) throw new ServiceException("already.bought");
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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
