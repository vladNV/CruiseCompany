package model.dao.mapper.join;

import model.dao.mapper.Mapper;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.PortMapper;
import model.entity.Excursion;
import model.entity.Port;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ExcursionPortJoin implements Mapper<Excursion> {
    private HashMap<Integer, Port> map;

    public ExcursionPortJoin() {
        this.map = new HashMap<>();
    }

    @Override
    public Excursion extract(ResultSet rs) throws SQLException {
        Port port = EntityMapper.extractIf(rs, new PortMapper());
        map.putIfAbsent(port.getId(), port);
        return Excursion.newExcursion()
                .id(rs.getInt("idexcursion"))
                .name(rs.getString("excursionname"))
                .price(rs.getLong("price"))
                .port(map.get(rs.getInt("idport")))
                .build();
    }
}
