package lab23;


public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();
        EmailNotification email = new EmailNotification();
        TelephoneNotification phone = new TelephoneNotification();

        logger.setNext(email);
        email.setNext(phone);

        Sensor sensor = new Sensor(logger);
        sensor.start();
    }
}
