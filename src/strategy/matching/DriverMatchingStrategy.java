package strategy.matching;

import model.Driver;
import model.Rider;

import java.io.Serializable;
import java.util.List;

public interface DriverMatchingStrategy extends Serializable {
    Driver matchDriver(List<Driver> drivers);
}
