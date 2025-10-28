package lab23;


public class Logger extends BaseNotificationHandler {
    @Override
    public void handle(SensorEvent event) {
        System.out.println("Event " + event + " was logged.");
        forward(event);
    }
}
