package lab23;


public class TelephoneNotification extends BaseNotificationHandler {
    @Override
    public void handle(SensorEvent event) {
        if (event.getType() == EventType.FIRE ||
                event.getType() == EventType.INTRUSION) {
            System.out.println("A call was made for event " + event);
        }
        forward(event);
    }
}
