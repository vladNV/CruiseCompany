package model.dao.mapper;

import model.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class EntityMapper {

    public static <T extends Entity> T extractIf(ResultSet set, Mapper<T> mapper)
            throws SQLException {
        return mapper.extract(set);
    }


    public static <T extends Entity> T extractNextIf(ResultSet set, Mapper<T> mapper)
            throws SQLException {
        if (set.next()) {
            return mapper.extract(set);
        }
        return null;
    }

    public static <T extends Entity> List<T>
    extractNextWhile(ResultSet set, Mapper<T> mapper) throws SQLException {
        List<T> generics = new ArrayList<>();
        while (set.next()) {
            generics.add(mapper.extract(set));
        }
        return generics;
    }

    public static int getKey(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public static boolean exist(ResultSet rs) throws SQLException {
        return rs.next();
    }

}
