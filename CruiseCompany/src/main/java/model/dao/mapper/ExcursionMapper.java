package model.dao.mapper;

import model.entity.Excursion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExcursionMapper implements Mapper <Excursion> {
    @Override
    public Excursion extract(ResultSet rs) throws SQLException {
        return Excursion.newExcursion()
                .id(rs.getInt("idexcursion"))
                .name(rs.getString("excursionname"))
                .price(rs.getLong("price"))
                .build();
    }
}
