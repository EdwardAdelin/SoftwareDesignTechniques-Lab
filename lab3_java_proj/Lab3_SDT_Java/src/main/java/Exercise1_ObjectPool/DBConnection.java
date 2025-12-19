package Exercise1_ObjectPool;

public class DBConnection {
    private final int id;

    public DBConnection(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void acquire(int clientId) {
        System.out.println("Connection " + id + " was acquired by client " + clientId);
    }

    public void release(int clientId) {
        System.out.println("Connection " + id + " was released by client " + clientId);
    }
}

