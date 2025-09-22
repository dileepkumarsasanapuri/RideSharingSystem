package observer;


import model.Driver;
import model.Rider;


public class NotificationService {
    private final Notifier notifier;

    public NotificationService(Notifier notifier) {
        this.notifier = notifier;
    }

    public void notifyRider(Rider rider, String message) {
        notifier.notifyRider(rider, message);
    }

    public void notifyDriver(Driver driver, String message) {
        notifier.notifyDriver(driver, message);
    }
}