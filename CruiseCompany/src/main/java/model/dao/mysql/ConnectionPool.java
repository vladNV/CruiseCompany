package model.dao.mysql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

public final class ConnectionPool  {
    private static ConnectionPool pool;
    private DataSource source;
    private static final ResourceBundle properties = ResourceBundle.getBundle("mysql");
    private final static Logger logger = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool (){
        // private constructor
        init();
    }

    public static ConnectionPool pool() {
        if (pool == null) {
            synchronized (ConnectionPool.class) {
                if (pool == null) {
                    logger.info("connection pool created");
                    pool = new ConnectionPool();
                }
            }
        }
        return pool;
    }

    private void init() {
        BasicDataSource source = new BasicDataSource();
        source.setUrl(properties.getString("url"));
        source.setUsername(properties.getString("name"));
        source.setPassword(properties.getString("password"));
        source.setDriverClassName(properties.getString("driver"));
        source.setMaxOpenPreparedStatements(100);
        source.setInitialSize(10);
        source.setMaxIdle(1);
        this.source = source;
    }

    public final Connection connect() {
        logger.info("connect");
        try {
            return this.source.getConnection();
        } catch (SQLException e) {
            logger.info("connection error", e);
            throw new RuntimeException(e);
        }
    }
}
