package model.dao.mapper.join;

import model.dao.mapper.Mapper;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.PortMapper;
import model.entity.Port;
import model.entity.Route;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class RoutePortJoin implements Mapper<Route> {
    private HashMap<Integer, Port> map;

    public RoutePortJoin() {
        this.map = new HashMap<>();
    }

    @Override
    public Route extract(ResultSet rs) throws SQLException {
        Port port = EntityMapper.extractIf(rs, new PortMapper());
        map.putIfAbsent(port.getId(), port);
        return Route.newRoute()
                .id(rs.getInt("idroute"))
                .arrival(rs.getTimestamp("arrival").toLocalDateTime())
                .departure(rs.getTimestamp("departure").toLocalDateTime())
                .name(rs.getString("routename"))
                .port(map.get(rs.getInt("idport")))
                .build();
    }
}
