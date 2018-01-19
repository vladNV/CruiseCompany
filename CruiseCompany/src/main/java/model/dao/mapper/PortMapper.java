package model.dao.mapper;

import model.entity.Port;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PortMapper {

    public static Port extractIf(ResultSet set) throws SQLException {
        if (set.next()) {
            extractPort(set);
        }
        return null;
    }

    public static List<Port> extractWhile(ResultSet set)
            throws SQLException {
        List<Port> Ports = new ArrayList<>();
        while (set.next()) {
            Ports.add(extractPort(set));
        }
        return Ports;
    }

    private static Port extractPort(ResultSet set) throws SQLException {
        return Port.newPort()
                .id(set.getInt("idport"))
                .name(set.getString("portname"))
                .city(set.getString("city"))
                .country(set.getString("country"))
                .build();
    }
    
}
