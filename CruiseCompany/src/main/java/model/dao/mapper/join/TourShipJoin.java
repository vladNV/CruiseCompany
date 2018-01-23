package model.dao.mapper.join;

import model.dao.mapper.Mapper;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.ShipMapper;
import model.entity.Ship;
import model.entity.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class TourShipJoin implements Mapper<Tour> {
    private HashMap<Integer, Ship> map;

    public TourShipJoin() {
        map = new HashMap<>();
    }

    @Override
    public Tour extract(ResultSet rs) throws SQLException {
        Ship ship = EntityMapper.extractIf(rs, new ShipMapper());
        map.putIfAbsent(ship.getId(), ship);
        return Tour.newTour()
                .id(rs.getInt("idtour"))
                .arrival(rs.getTimestamp("arrival").toLocalDateTime())
                .departure(rs.getTimestamp("departure").toLocalDateTime())
                .name(rs.getString("tourname"))
                .region(rs.getString("region"))
                .ship(map.get(rs.getInt("idship")))
                .build();
    }
}
