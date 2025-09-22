package observer;

import model.Driver;
import model.Rider;

import java.util.ArrayList;
import java.util.List;


public class CompositeNotifier implements Notifier {
    private final List<Notifier> notifiers = new ArrayList<>();

    public void registerNotifier(Notifier notifier) {
        if (notifier != null) notifiers.add(notifier);
    }

    public void removeNotifier(Notifier notifier) {
        notifiers.remove(notifier);
    }

    @Override
    public void notifyRider(Rider rider, String message) {
        notifiers.forEach(n -> n.notifyRider(rider, message));
    }

    @Override
    public void notifyDriver(Driver driver, String message) {
        notifiers.forEach(n -> n.notifyDriver(driver, message));
    }
}
