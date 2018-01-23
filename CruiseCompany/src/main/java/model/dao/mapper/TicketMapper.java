package model.dao.mapper;

import model.entity.Ticket;
import model.entity.TicketClass;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements Mapper<Ticket> {
    @Override
    public Ticket extract(ResultSet rs) throws SQLException {
        return Ticket.newTicket()
                .id(rs.getInt("idticket"))
                .arrival(rs.getTimestamp("arrival").toLocalDateTime())
                .departure(rs.getTimestamp("departure").toLocalDateTime())
                .price(rs.getLong("price"))
                .type(TicketClass.valueOf(rs.getString("type")))
                .build();
    }
}
