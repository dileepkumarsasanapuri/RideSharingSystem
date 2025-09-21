package strategy.matching;

import model.Driver;
import model.Ride;
import model.Rider;

import java.util.List;

public class NearestDriverMatching implements DriverMatchingStrategy{
    @Override
    public Driver matchDriver(Rider rider, List<Driver> availableDriver){
        if(!availableDriver.isEmpty()){
            return availableDriver.get(0);
        }
        return null;
    }
}
