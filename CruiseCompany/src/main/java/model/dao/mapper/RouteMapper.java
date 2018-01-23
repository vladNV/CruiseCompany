package model.dao.mapper;

import model.entity.Route;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteMapper implements Mapper<Route> {
    @Override
    public Route extract(ResultSet rs) throws SQLException {
        return Route.newRoute()
                .id(rs.getInt("idroute"))
                .arrival(rs.getTimestamp("arrival").toLocalDateTime())
                .departure(rs.getTimestamp("departure").toLocalDateTime())
                .name(rs.getString("routename"))
                .build();
    }
}
