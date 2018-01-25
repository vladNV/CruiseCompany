package model.dao.mysql;

import model.dao.interfaces.TicketDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.Mapper;
import model.dao.mapper.TicketMapper;
import model.dao.mapper.aggregation.TicketAmount;
import model.entity.Ticket;
import model.entity.Tour;
import model.exceptions.ServiceException;
import model.dao.mapper.AggregateOperation;
import model.entity.TicketClass;
import org.apache.log4j.Logger;

import static model.dao.queries.TicketSQL.*;
import java.sql.*;
import java.util.List;

public class TicketMySQL implements TicketDAO {
    private final Connection connection;
    private final static Logger logger = Logger.getLogger(TicketMySQL.class);

    TicketMySQL(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Ticket ticket) {
        logger.info("insert");
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
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Ticket ticket) {
        logger.info("update");
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setInt(1, ticket.getUser().getId());
            statement.setInt(2, ticket.getTour().getId());
            statement.setTimestamp(3, Timestamp.valueOf(ticket.getArrival()));
            statement.setTimestamp(4, Timestamp.valueOf(ticket.getDeparture()));
            statement.setLong(5, ticket.getPrice());
            statement.setString(6, String.valueOf(ticket.getType()));
            statement.execute();
        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Ticket findById(int id) {
        logger.info("find by id");
        try (PreparedStatement statement = connection.prepareStatement(FIND)){
            statement.setInt(1, id);
            Mapper<Ticket> mapper = new TicketMapper();
            return EntityMapper.extractNextIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<AggregateOperation<Integer, Ticket>> ticketForCategory(int tourId) {
        logger.info("ticket for category");
        try (PreparedStatement statement = connection.prepareStatement(QUANTITY_TICKET)){
            statement.setInt(1, tourId);
            Mapper<AggregateOperation<Integer, Ticket>> mapper = new TicketAmount();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> ticketsForId(int id) {
        try (PreparedStatement statement = connection.prepareStatement(USER_TICKETS)){
            statement.setInt(1, id);
            Mapper<Ticket> mapper = new TicketMapper();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public Ticket findTicketByType(TicketClass type, int tourId) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_TYPE)) {
            statement.setString(1, String.valueOf(type));
            statement.setInt(2, tourId);
            Mapper<Ticket> mapper = new TicketMapper();
            return EntityMapper.extractNextIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTicket(Ticket ticket, int userId) throws ServiceException {
        logger.info("update ticket");
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_TICKET)){
            statement.setInt(1, userId);
            statement.setInt(2, ticket.getAmountPassengers());
            statement.setInt(3, ticket.getId());
            int update = statement.executeUpdate();
            if(update == 0) throw new ServiceException("ticket.was.bought");
        } catch (SQLException e) {
            try {
                logger.info("connection update ticket rollback");
                connection.rollback();
            } catch (SQLException roll) {
                logger.error("rollback error ", e);
                throw new RuntimeException(roll);
            }
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void existTicket(int userId, int tourId)
            throws ServiceException {
        logger.info("exist ticket");
        try (PreparedStatement statement = connection.prepareStatement(EXIST_TICKET)) {
            statement.setInt(1, tourId);
            statement.setInt(2, userId);
            boolean result = EntityMapper.exist(statement.executeQuery());
            if (result) throw new ServiceException("already.bought");
        } catch (SQLException e) {
            try {
                logger.info("connection exist ticket rollback");
                connection.rollback();
            } catch (SQLException roll) {
                logger.error("rollback error ", e);
                throw new RuntimeException(roll);
            }
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setTicketsOnTour(Tour tour) {
        logger.info("set tickets on tour");
        try (PreparedStatement statement = connection.prepareStatement(INSERT)){
            for (Ticket ticket : tour.getTickets()) {
                statement.setInt(1, tour.getId());
                statement.setTimestamp(2, Timestamp.valueOf(tour.getArrival()));
                statement.setTimestamp(3, Timestamp.valueOf(tour.getDeparture()));
                statement.setLong(4, ticket.getPrice());
                statement.setString(5, String.valueOf(ticket.getType()));
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            try {
                logger.info("connection set tickets on tour rollback");
                connection.rollback();
            } catch (SQLException roll) {
                logger.error("rollback error ", e);
                throw new RuntimeException(roll);
            }
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
