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
                .arrival(rs.getTimestamp("arrival").toLocalDateTime())
                .departure(rs.getTimestamp("departure").toLocalDateTime())
                .price(rs.getLong("price"))
                .type(TicketClass.valueOf(rs.getString("type")))
                .tour(map.get(rs.getInt("idtour")))
                .build();
    }
}