package observer;

import model.Driver;
import model.Rider;


public class SMSNotifier implements Notifier {
    @Override
    public void notifyRider(Rider rider, String message) {
        System.out.println("[SMS to " + rider.getPhone() + "] " + message);
    }

    @Override
    public void notifyDriver(Driver driver, String message) {
        System.out.println("[SMS to " + driver.getPhone() + "] " + message);
    }
}
