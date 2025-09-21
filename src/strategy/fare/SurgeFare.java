package strategy.fare;

public class SurgeFare implements FareStrategy {
    private static final double base_fare=10;
    private static final double surge_fare=1.5;

    @Override
    public double calculateFare(double distance){
        return distance*base_fare*surge_fare;
    }
}
