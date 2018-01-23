package model.dao.mapper;

import model.entity.Ship;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipMapper implements Mapper<Ship> {
    @Override
    public Ship extract(ResultSet rs) throws SQLException {
        return Ship.newShip()
                .id(rs.getInt("idship"))
                .name(rs.getString("shipname"))
                .capacity(rs.getInt("capacity"))
                .build();
    }
}
