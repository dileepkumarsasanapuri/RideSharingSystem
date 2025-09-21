package strategy.matching;

import model.Driver;
import model.Ride;
import model.Rider;

import java.util.Comparator;
import java.util.List;

public class HighestRatedDrivingMatching implements DriverMatchingStrategy {
    @Override
    public Driver matchDriver(Rider rider, List<Driver> availableDrivers){
        return availableDrivers.stream()
                .max(Comparator.comparingDouble(Driver::getRating))
                .orElse(null);
    }
}
