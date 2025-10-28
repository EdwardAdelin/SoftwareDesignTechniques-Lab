package lab23;

public interface NotificationHandler {
    void setNext(NotificationHandler next);
    void handle(SensorEvent event);
}
