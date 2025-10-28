package lab23;


public class EmailNotification extends BaseNotificationHandler {
    @Override
    public void handle(SensorEvent event) {
        if (event.getType() == EventType.FIRE ||
                event.getType() == EventType.INTRUSION ||
                event.getType() == EventType.WATER) {
            System.out.println("The email is sent for the event: " + event);
        }
        forward(event);
    }
}

