package model.dao.mapper;

import model.entity.Port;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PortMapper implements Mapper<Port> {
    @Override
    public Port extract(ResultSet rs) throws SQLException {
        return Port.newPort()
                .id(rs.getInt("idport"))
                .name(rs.getString("portname"))
                .city(rs.getString("city"))
                .country(rs.getString("country"))
                .build();
    }
}
