package strategy.fare;

public class NormalFare implements FareStrategy {
    private final double base_fare;
    private final double perKm;

    public NormalFare(double base_fare,double perKm){
        if(base_fare<0 || perKm<0) throw new IllegalArgumentException("fare must be >=0");
        this.base_fare=base_fare;
        this.perKm=perKm;
    }
    @Override
    public double calculateFare(double distance){
        if(distance<0) throw new IllegalArgumentException("distance must be>=0");
        return base_fare+perKm*distance;
    }
}
