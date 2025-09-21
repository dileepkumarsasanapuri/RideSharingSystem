package service;
import model.*;
import observer.NotificationService;
import strategy.fare.FareStrategy;
import strategy.payment.PaymentStrategy;

import java.util.ArrayList;
import java.util.UUID;

public class RideService {


    private NotificationService nfs;
    public RideService(NotificationService nfs){
        this.nfs=nfs;
    }
    public Ride bookRide(Rider rider, Driver driver, double distance, FareStrategy fareStrategy, PaymentStrategy paymentStrategy){
        if(!driver.isAvailable()){
            throw new RuntimeException("Driver not available! ");
        }
        String rideId= UUID.randomUUID().toString();
        Ride ride=new Ride(rideId,rider,driver,distance,fareStrategy,paymentStrategy);

        driver.setAvailable(false);
        nfs.notifyAll("Ride booked with "+driver.getName()+"of Fare Rs."+ride.getFare());
        return ride;
    }

    public void startRide(Ride ride){
        ride.startRide();
        nfs.notifyAll("Ride "+ride.getRideId()+" started with driver "+ride.getDriver().getName());

    }

    public void completeRide(Ride ride){
        ride.completeRide();
        ride.makePayment();
        ride.getDriver().setAvailable(true);
        nfs.notifyAll("Ride "+ride.getRideId()+" completed. Paid Rs."+ride.getFare());

    }
}
