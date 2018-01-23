package model.dao.mapper.aggregation;

import model.dao.mapper.AggregateOperation;
import model.dao.mapper.Mapper;
import model.entity.Ticket;
import model.entity.TicketClass;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketAmount implements Mapper<AggregateOperation<Integer, Ticket>> {
    @Override
    public AggregateOperation<Integer, Ticket>
    extract(ResultSet rs) throws SQLException {
        Ticket t = Ticket.newTicket()
                .arrival(rs.getTimestamp("arrival").toLocalDateTime())
                .departure(rs.getTimestamp("departure").toLocalDateTime())
                .price(rs.getLong("price"))
                .type(TicketClass.valueOf(rs.getString("type")))
                .build();
        Integer amount = rs.getInt("amount");
        return new AggregateOperation<>(amount, t);
    }
}
