package strategy.fare;

public class NormalFare implements FareStrategy {
    private static final double base_fare=10;
    @Override
    public double calculateFare(double distance){
        return distance*base_fare;
    }
}
