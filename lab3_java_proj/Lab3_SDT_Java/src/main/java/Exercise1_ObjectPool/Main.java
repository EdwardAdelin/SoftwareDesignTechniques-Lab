package Exercise1_ObjectPool;

public class Main {
    public static void main(String[] args) {
        int numberOfClients = 10; // simulate 10 concurrent clients

        for (int i = 1; i <= numberOfClients; i++) {
            Thread t = new Thread(new Client(i));
            t.start();
        }
    }
}