package observer;

import model.Driver;
import model.Rider;

public interface Notifier {
    void notifyRider(Rider rider, String message);
    void notifyDriver(Driver driver, String message);
}
