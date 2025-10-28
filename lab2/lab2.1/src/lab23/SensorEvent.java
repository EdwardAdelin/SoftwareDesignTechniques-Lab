package lab23;

import java.time.LocalDateTime;

public class SensorEvent {
    private EventType type;
    private LocalDateTime timestamp;
    private String location;

    public SensorEvent(EventType type, String location) {
        this.type = type;
        this.location = location;
        this.timestamp = LocalDateTime.now();
    }

    public EventType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return String.format("[%s] Event: %s at %s", timestamp, type, location);
    }
}