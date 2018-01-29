package model.dao.mysql;

import model.dao.interfaces.PortDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.Mapper;
import model.dao.mapper.PortMapper;
import model.entity.Port;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

import static model.dao.queries.PortSQL.*;

public class PortMySQL implements PortDAO {
    private final Connection connection;
    private final static Logger logger = Logger.getLogger(PortMySQL.class);

    PortMySQL(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Port port) {
        logger.info("insert");
        try (PreparedStatement statement = connection
                .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, port.getName());
            statement.setString(2, port.getCity());
            statement.setString(3, port.getCountry());
            return EntityMapper.getKey(statement.getGeneratedKeys());
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Port port) {
        logger.info("update");
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, port.getName());
            statement.setString(2, port.getCity());
            statement.setString(3, port.getCountry());
            statement.setInt(4, port.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Port findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND)){
            statement.setInt(1, id);
            return EntityMapper.extractNextIf(statement.executeQuery(), new PortMapper());
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Port> findAll() {
        logger.info("find all");
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_ALL)){
            Mapper<Port> mapper = new PortMapper();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
