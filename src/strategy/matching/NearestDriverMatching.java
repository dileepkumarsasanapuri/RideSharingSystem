package strategy.matching;

import model.Driver;
import model.Ride;
import model.Rider;

import java.util.List;
import java.util.Random;

public class NearestDriverMatching implements DriverMatchingStrategy{
    private final Random rand = new Random();

    @Override
    public Driver matchDriver(List<Driver> drivers){
        if(drivers==null || drivers.isEmpty()) return null;
        Driver best=null;
        double bestDist=Double.MAX_VALUE;
        for(Driver d:drivers){
            double dist=0.5 +rand.nextDouble()*10.0;
            if(dist<bestDist){
                bestDist=dist;
                best=d;
            }
        }
        return best;
    }
}
