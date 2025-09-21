package strategy.matching;

import model.Driver;
import model.Rider;

import java.util.List;

public interface DriverMatchingStrategy {
    Driver matchDriver(Rider rider, List<Driver> availableDriver);
}
