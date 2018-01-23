package model.dao.mapper;

import model.entity.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TourMapper implements Mapper<Tour> {
    @Override
    public Tour extract(ResultSet rs) throws SQLException {
        return Tour.newTour()
                .id(rs.getInt("idtour"))
                .arrival(rs.getTimestamp("arrival").toLocalDateTime())
                .departure(rs.getTimestamp("departure").toLocalDateTime())
                .name(rs.getString("tourname"))
                .region(rs.getString("region"))
                .build();
    }
}
