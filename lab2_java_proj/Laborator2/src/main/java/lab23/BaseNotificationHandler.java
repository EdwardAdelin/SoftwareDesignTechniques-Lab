package lab23;

public abstract class BaseNotificationHandler implements NotificationHandler {
    protected NotificationHandler next;

    @Override
    public void setNext(NotificationHandler next) {
        this.next = next;
    }

    protected void forward(SensorEvent event) {
        if (next != null) {
            next.handle(event);
        }
    }
}
