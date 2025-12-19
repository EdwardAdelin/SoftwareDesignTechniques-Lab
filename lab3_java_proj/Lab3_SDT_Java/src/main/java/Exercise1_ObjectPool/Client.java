package Exercise1_ObjectPool;

public class Client implements Runnable {
    private final int clientId;

    public Client(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public void run() {
        DBConnectionPool pool = DBConnectionPool.getInstance();
        DBConnection connection = pool.acquireConnection(clientId);

        try {
            // Simulate work with the connection
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pool.releaseConnection(connection, clientId);
    }
}
