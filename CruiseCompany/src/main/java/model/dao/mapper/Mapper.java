package model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {

    T extract(ResultSet rs) throws SQLException;

}
