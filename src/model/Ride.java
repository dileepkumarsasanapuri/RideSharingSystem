package model;

import strategy.fare.FareStrategy;
import strategy.payment.PaymentStrategy;
public class Ride {
    private String rideId;
    private Rider rider;
    private Driver driver;
    private double distance;
    private double fare;
    private RideStatus status;
    private String source;
    private String dest;
    private FareStrategy farestr;
    private PaymentStrategy paystr;
    public Ride(String rideId,Rider rider,Driver driver,double distance,String source,String dest,FareStrategy farestr,PaymentStrategy paystr){
        this.distance=distance;
        this.rider=rider;
        this.source=source;
        this.dest=dest;
        this.rideId=rideId;
        this.driver=driver;
        this.farestr=farestr;
        this.paystr=paystr;
        this.status=RideStatus.ONGOING;
        this.fare=farestr.calculateFare(distance);
    }

    public String getRideId() {
        return rideId;
    }
    public Rider getRider() {
        return rider;
    }
    public Driver getDriver() {
        return driver;
    }
    public double getFare() {
        return fare;
    }
    public RideStatus getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public String getDest() {
        return dest;
    }



    public void completeRide(){
        this.status=RideStatus.COMPLETED;
    }

    public void makePayment(){
        paystr.pay(fare);
    }
    @Override
    public String toString() {
        return "Ride{" +
                "rider=" + rider.getName() +
                ", driver=" + driver.getName() +
                ", source='" + source + '\'' +
                ", destination='" + dest + '\'' +
                ", fare=" + fare +
                ", status=" + status +
                '}';
    }
}
