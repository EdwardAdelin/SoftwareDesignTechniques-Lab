package lab23;


import java.util.Random;

public class Sensor extends Thread {
    private final NotificationHandler chain;
    private final Random random = new Random();
    private final String[] locations = {"Living Room", "Kitchen", "Garage", "Bedroom"};

    public Sensor(NotificationHandler chain) {
        this.chain = chain;
    }

    @Override
    public void run() {
        while (true) {
            EventType type = EventType.values()[random.nextInt(EventType.values().length)];
            String location = locations[random.nextInt(locations.length)];
            SensorEvent event = new SensorEvent(type, location);
            chain.handle(event);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
