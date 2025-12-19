package Exercise1_ObjectPool;

import java.util.ArrayList;
import java.util.List;

public class DBConnectionPool {
    private static DBConnectionPool instance;
    private final List<DBConnection> availableConnections;
    private final List<DBConnection> inUseConnections;
    private static final int MAX_CONNECTIONS = 5;

    private DBConnectionPool() {
        availableConnections = new ArrayList<>();
        inUseConnections = new ArrayList<>();
        for (int i = 1; i <= MAX_CONNECTIONS; i++) {
            availableConnections.add(new DBConnection(i));
        }
    }

    public static synchronized DBConnectionPool getInstance() {
        if (instance == null) {
            instance = new DBConnectionPool();
        }
        return instance;
    }

    public synchronized DBConnection acquireConnection(int clientId) {
        while (availableConnections.isEmpty()) {
            try {
                wait(); // wait if no connection is available
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        DBConnection conn = availableConnections.remove(0);
        inUseConnections.add(conn);
        conn.acquire(clientId);
        return conn;
    }

    public synchronized void releaseConnection(DBConnection conn, int clientId) {
        inUseConnections.remove(conn);
        availableConnections.add(conn);
        conn.release(clientId);
        notifyAll(); // wake up waiting threads
    }
}
