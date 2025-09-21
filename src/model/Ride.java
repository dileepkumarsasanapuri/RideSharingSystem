package model;

import strategy.fare.FareStrategy;
import strategy.payment.PaymentStrategy;
public class Ride {
    private String rideId;
    private Rider rider;
    private Driver driver;
    private double distance;
    private double fare;
    private String status;
    private FareStrategy farestr;
    private PaymentStrategy paystr;
    public Ride(String rideId,Rider rider,Driver driver,double distance,FareStrategy farestr,PaymentStrategy paystr){
        this.distance=distance;
        this.rider=rider;
        this.rideId=rideId;
        this.driver=driver;
        this.farestr=farestr;
        this.paystr=paystr;
        this.status="BOOKED";
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
    public String getStatus() {
        return status;
    }

    public void startRide(){
        this.status="ONGOING";
    }

    public void completeRide(){
        this.status="COMPLETED";
    }

    public void makePayment(){
        paystr.pay(fare);
    }
}
