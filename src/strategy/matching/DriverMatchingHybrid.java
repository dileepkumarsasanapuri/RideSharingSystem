package strategy.matching;

import model.Driver;

import java.util.List;
import java.util.Random;

public class DriverMatchingHybrid implements DriverMatchingStrategy{
    private final Random rand = new Random();

    @Override
    public Driver matchDriver(List<Driver> drivers) {
        if (drivers == null || drivers.isEmpty()) return null;
        Driver best = null;
        double bestScore = Double.NEGATIVE_INFINITY;
        for (Driver d : drivers) {
            double distanceScore = 1.0 / (0.5 + rand.nextDouble() * 10.0); // higher for closer
            double ratingScore = d.getRating() / 5.0;
            double score = 0.6 * ratingScore + 0.4 * distanceScore; // weights
            if (score > bestScore) {
                bestScore = score;
                best = d;
            }
        }
        return best;
    }
}
