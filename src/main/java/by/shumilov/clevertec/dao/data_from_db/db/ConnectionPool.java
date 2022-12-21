package by.shumilov.clevertec.dao.data_from_db.db;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;


public final class ConnectionPool {

    private static final Properties properties = new Properties();

    private String url;
    private String user;
    private String password;
    private int maxSize;
    private int checkConnectionTimeout;

    private final BlockingQueue<PooledConnection> freeConnections = new LinkedBlockingQueue<>();
    private final Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

    private ConnectionPool() {
    }

    public synchronized Connection getConnection() {
        PooledConnection connection = null;
        while (connection == null) {
            try {
                if (!freeConnections.isEmpty()) {
                    connection = freeConnections.take();
                    if (!connection.isValid(checkConnectionTimeout)) {
                        try {
                            connection.getConnection().close();
                        } catch (SQLException e) {
                        }
                        connection = null;
                    }
                } else if (usedConnections.size() < maxSize) {
                    connection = createConnection();
                } else {
                    System.err.println("The limit of number of database connections is exceeded");
                }
            } catch (InterruptedException | SQLException e) {
                System.err.println("It is impossible to connect to a database");
            }
        }
        usedConnections.add(connection);
        System.err.println(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
        return connection;
    }

    synchronized void freeConnection(PooledConnection connection) {
        try {
            if (connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
                System.err.println(String.format("Connection was returned into pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
            }
        } catch (SQLException | InterruptedException e1) {
            System.err.println("It is impossible to return database connection into pool");
            try {
                connection.getConnection().close();
            } catch (SQLException e2) {
            }
        }
    }

    public synchronized void init() {
        try {
            destroy();
            properties.load(new FileReader(getFileFromResource("datares/database.properties")));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
            this.url = (String) properties.get("db.url");
            this.user = (String) properties.get("user");
            this.password = (String) properties.get("password");
            this.maxSize = Integer.parseInt(String.valueOf((properties.get("poolsize"))));
            this.checkConnectionTimeout = Integer.parseInt(String.valueOf(properties.get("connectionTimeout")));
            int startSize = Integer.parseInt(String.valueOf(properties.get("startSize")));
            for (int counter = 0; counter < startSize; counter++) {
                freeConnections.put(createConnection());
            }
        } catch (SQLException | InterruptedException | URISyntaxException e) {
            System.err.println("It is impossible to initialize connection pool");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    private PooledConnection createConnection() throws SQLException {
        return new PooledConnection(DriverManager.getConnection(url, user, password));
    }

    public synchronized void destroy() {
        usedConnections.addAll(freeConnections);
        freeConnections.clear();
        for (PooledConnection connection : usedConnections) {
            try {
                connection.getConnection().close();
            } catch (SQLException ignored) {
            }
        }
        usedConnections.clear();
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }

    public static File getFileFromResource(final String fileName)
            throws URISyntaxException {
        ClassLoader classLoader = ConnectionPool.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource != null) {
            return new File(resource.toURI());
        } else {
            throw new URISyntaxException(fileName, ": couldn't be parsed.");
        }
    }

}
