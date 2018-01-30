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
                .place(rs.getInt("place"))
                .person(rs.getString("person"))
                .price(rs.getLong("price"))
                .type(TicketClass.valueOf(rs.getString("type")))
                .bonus(rs.getString("bonus"))
                .build();
    }
}
