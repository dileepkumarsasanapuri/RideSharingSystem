package service;
import model.*;
import observer.NotificationService;
import strategy.fare.FareStrategy;
import strategy.fare.NormalFare;
import strategy.matching.DriverMatchingStrategy;
import strategy.payment.CashPayment;
import strategy.payment.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RideService {
    private List<Rider> riders=new ArrayList<>();
    private List<Driver> drivers=new ArrayList<>();
    private List<Ride> rides=new ArrayList<>();

    private NotificationService nfs=new NotificationService();
    private DriverMatchingStrategy dms;
    private PaymentStrategy psr;
    public RideService(DriverMatchingStrategy dms){
        this.dms=dms;
    }

    public void addRider(Rider rider) {
        riders.add(rider);
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }
    public void requestRide(Rider rider,String src,String dest,double distance,FareStrategy fsr,PaymentStrategy psr){

        List<Driver> availableDrivers=drivers.stream()
                .filter(Driver::isAvailable)
                .toList();

        Driver matchedDriver=dms.matchDriver(rider,availableDrivers);
        if(matchedDriver==null){
            System.out.println("No drivers available currently! for rider: "+rider.getName());
            return;
        }

        matchedDriver.setAvailable(false);

        String rideId= UUID.randomUUID().toString();
        Ride ride=new Ride.RideBuilder()
                .setRiderId(rideId)
                .setRider(rider)
                .setDriver(matchedDriver)
                .setDistance(14.3)
                .setSource("MG Road")
                .setDest("Airport")
                .setFareStrategy(fsr)
                .setPaymentStrategy(psr)
                .build();


        rides.add(ride);

        nfs.notify(rider,"Ride Booked ! Ride Id:"+rideId+" with Driver "+matchedDriver.getName()+" of Fare: "+ride.getFare());
        nfs.notify(matchedDriver,"New Ride Assigned! Ride Id:"+rideId+" Pick rider "+rider.getName()+" at "+src+" dropping at "+dest);
    }

    public void completeRide(Ride ride){
        ride.completeRide();
        Driver dr=ride.getDriver();
        dr.setAvailable(true);

        nfs.notify(ride.getRider(),"Ride Completed ! Ride Id:"+ride.getRideId()+" with Driver "+ride.getDriver().getName()+"Pay Fare: "+ride.getFare());
        nfs.notify(dr,"Ride Completed! Ride Id:"+ride.getRideId()+" Recieve amount of Rs."+ride.getFare());
        ride.getPaystr().pay(ride.getFare());
    }
    public List<Ride> getAllRides(){
        return rides;
    }

}
