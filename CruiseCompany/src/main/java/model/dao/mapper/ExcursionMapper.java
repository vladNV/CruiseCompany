package model.dao.mapper;

import model.entity.Excursion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExcursionMapper {

    public static Excursion extractIf(ResultSet set) throws SQLException {
        if (set.next()) {
            extractExcursion(set);
        }
        return null;
    }

    public static List<Excursion> extractWhile(ResultSet set)
            throws SQLException {
        List<Excursion> Ports = new ArrayList<>();
        while (set.next()) {
            Ports.add(extractExcursion(set));
        }
        return Ports;
    }

    private static Excursion extractExcursion(ResultSet set) throws SQLException {
        return Excursion.newExcursion()
                .id(set.getInt("idexcursion"))
                .name(set.getString("excursionname"))
                .price(set.getLong("price"))
                .build();
    }
}
