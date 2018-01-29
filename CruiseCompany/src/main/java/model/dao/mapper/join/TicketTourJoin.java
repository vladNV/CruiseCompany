package model.dao.mapper.join;

import model.dao.mapper.Mapper;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.TourMapper;
import model.entity.Ticket;
import model.entity.TicketClass;
import model.entity.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class TicketTourJoin implements Mapper<Ticket> {
    private HashMap<Integer, Tour> map;

    public TicketTourJoin() {
        map = new HashMap<>();
    }

    @Override
    public Ticket extract(ResultSet rs) throws SQLException {
        Tour tour = EntityMapper.extractIf(rs, new TourMapper());
        map.putIfAbsent(tour.getId(), tour);
        return Ticket.newTicket()
                .id(rs.getInt("idticket"))
                .person(rs.getString("person"))
                .place(rs.getInt("place"))
                .price(rs.getLong("price"))
                .amountPassengers(rs.getInt("amount_passengers"))
                .type(TicketClass.valueOf(rs.getString("type")))
                .tour(map.get(rs.getInt("idtour")))
                .build();
    }
}
