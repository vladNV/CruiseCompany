package model.dao.mysql;

        import model.dao.interfaces.UserDAO;
        import model.dao.mapper.EntityMapper;
        import model.dao.mapper.EnumMapper;
        import model.dao.mapper.Mapper;
        import model.dao.mapper.UserMapper;
        import model.entity.User;

        import java.sql.*;
        import java.util.List;

public class UserMySQL implements UserDAO {
    private final Connection connection;
    private int limit;
    private int offset;

    private static final String INSERT =
            "insert into user(login, password, email) values (?, ?, ?)";
    private static final String UPDATE =
            "update user set login = ?, password = ?, email = ?, role = ? where iduser = ?";
    private static final String FIND =
            "select * from user where iduser = ?";
    private static final String FIND_ALL =
            "select * from user limit ?, ?";
    private static final String FIND_BY_LOGIN =
            "select * from user where email like ? limit 1";

    public UserMySQL(final Connection connection) {
        this.connection = connection;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public int insert(User user) {
        try (PreparedStatement statement = connection
                .prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.executeUpdate();
            return EntityMapper.getKey(statement.getGeneratedKeys());
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
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
            statement.executeUpdate();
        } catch (SQLException e) {
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
            Mapper<User> mapper = EntityMapper.mapperFactory(EnumMapper.UserMapper);
            return EntityMapper.extractIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            Mapper<User> mapper = EntityMapper.mapperFactory(EnumMapper.UserMapper);
            return EntityMapper.extractWhile(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public User findByEmail(String email) {
        try (PreparedStatement statement = connection
                .prepareStatement(FIND_BY_LOGIN)){
            statement.setString(1, email);
            Mapper<User> mapper = EntityMapper.mapperFactory(EnumMapper.UserMapper);
            return EntityMapper.extractIf(statement.executeQuery(), mapper);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
