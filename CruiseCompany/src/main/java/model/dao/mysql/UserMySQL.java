package model.dao.mysql;

import model.dao.interfaces.UserDAO;
import model.dao.mapper.EntityMapper;
import model.dao.mapper.Mapper;
import model.dao.mapper.UserMapper;
import model.entity.User;
import model.exceptions.ServiceException;
import model.exceptions.UniqueException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;
import static model.dao.queries.UserSQL.*;

public class UserMySQL implements UserDAO {
    private final Connection connection;
    private final static Logger logger = Logger.getLogger(UserMySQL.class);

    UserMySQL(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(User user) {
        logger.info("insert");
        try (PreparedStatement statement = connection
                .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.execute();
            return EntityMapper.getKey(statement.getGeneratedKeys());
        } catch (SQLIntegrityConstraintViolationException e) {
            logger.error(e.getMessage(), e);
            throw new UniqueException("such email isn't unique", user.getEmail());
        } catch (SQLException e) {
            logger.error(e.getSQLState(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, String.valueOf(user.getRole()));
            statement.setInt(5, user.getId());
            statement.execute();
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
    public User findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND)){
            statement.setInt(1, id);
            Mapper<User> mapper = new UserMapper();
            return EntityMapper.extractNextIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            Mapper<User> mapper = new UserMapper();
            return EntityMapper.extractNextWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_BY_LOGIN)){
            statement.setString(1, email);
            Mapper<User> mapper = new UserMapper();
            return EntityMapper.extractNextIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void existUser(long card, int CVV) throws ServiceException {
       logger.info("transaction exist user card " + card + ", cvv " + CVV);
       try (PreparedStatement statement = connection.prepareStatement(EXIST_CARD)){
           statement.setLong(1, card);
           statement.setInt(2, CVV);
           if (! statement.executeQuery().next()) {
               throw new ServiceException("account.notexist");
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public void takeMoney(long card, int CVV, long money) throws ServiceException {
        logger.info("transaction take money from card " + card + ", money " + money);
        try (PreparedStatement statement = connection.prepareStatement(TAKE_MONEY)){
            statement.setLong(1, money);
            statement.setLong(2, card);
            statement.setInt(3, CVV);
            statement.setLong(4, money);
            int update = statement.executeUpdate();
            if(update == 0) throw new ServiceException("have.not.money");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException roll) {
                logger.error("rollback error ", roll);
                throw new RuntimeException(roll);
            }
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void putMoney(long card, long money) {
        logger.info("transaction put money on card " + card + ", money " + money);
        try (PreparedStatement statement = connection.prepareStatement(PUT_MONEY)){
            statement.setLong(1, money);
            statement.setLong(2, card);
            int update = statement.executeUpdate();
            if (update == 0) throw new SQLException("payment error!");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException roll) {
                logger.error("rollback error ", roll);
                throw new RuntimeException(roll);
            }
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
