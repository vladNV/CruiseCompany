package model.dao.mysql;

import model.dao.interfaces.TicketDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.Mapper;
import model.dao.mapper.TicketMapper;
import model.dao.mapper.aggregation.TicketAmount;
import model.dao.mapper.join.TicketTourJoin;
import model.entity.Ticket;
import model.entity.Tour;
import model.entity.User;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Ticket ticket) {
        logger.info("update");
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setInt(1, ticket.getUser().getId());
            statement.setInt(2, ticket.getTour().getId());
            statement.setString(3, ticket.getPerson());
            statement.setInt(4, ticket.getPlace());
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
    public List<Ticket> tourTickets(int tourId) {
        try (PreparedStatement statement = connection.prepareStatement(TICKETS_TOUR)){
            statement.setInt(1, tourId);
            TicketMapper map = new TicketMapper();
            return EntityMapper.extractNextWhile(statement.executeQuery(), map);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
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
    public List<Ticket> userTickets(int userId) {
        try (PreparedStatement statement = connection.prepareStatement(USER_TICKETS)){
            statement.setInt(1, userId);
            Mapper<Ticket> mapper = new TicketTourJoin();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ticket getTourTicket(int ticketId) {
        try (PreparedStatement statement = connection.prepareStatement(CHOOSE_TICKET)){
            statement.setInt(1, ticketId);
            Mapper<Ticket> map = new TicketTourJoin();
            return EntityMapper.extractNextIf(statement.executeQuery(), map);
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
            statement.setString(3, ticket.getPerson());
            statement.setInt(4, ticket.getId());
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
            int iterator = 1;
            for (Ticket ticket : tour.getTickets()) {
                statement.setInt(1, tour.getId());
                statement.setTimestamp(2, Timestamp.valueOf(tour.getArrival()));
                statement.setTimestamp(3, Timestamp.valueOf(tour.getDeparture()));
                statement.setLong(4, ticket.getPrice());
                statement.setString(5, String.valueOf(ticket.getType()));
                statement.setInt(6, iterator++);
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
