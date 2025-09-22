package service;
import factory.CardPaymentFactory;
import factory.UPIPaymentFactory;
import model.*;
import observer.NotificationService;
import observer.Notifier;
import storage.Database;
import strategy.fare.FareStrategy;
import strategy.fare.NormalFare;
import strategy.matching.DriverMatchingStrategy;
import strategy.payment.CashPayment;
import strategy.payment.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class RideService{
    private final List<Rider> riders;
    private final List<Driver> drivers;
    private final List<Ride> rides;
    private final DriverMatchingStrategy dms;
    private final Notifier notifier;
    private final Database db;
    public RideService(DriverMatchingStrategy dms,Notifier notifier,Database db){
        this.dms=dms;
        this.db=db;
        this.notifier=notifier;
        this.riders=db.loadRiders();
        this.drivers=db.loadDrivers();
        this.rides=db.loadRides();
    }

    public void addRider(Rider rider){
        if(rider==null) throw new IllegalArgumentException("Rider cannot be null");
        riders.removeIf(r->r.getId().equals((rider.getId())));
        riders.add(rider);
        db.saveRider(rider);
    }

    public void addDriver(Driver driver){
        if(driver==null) throw new IllegalArgumentException("Driver cannot be null");
        drivers.removeIf(d->d.getId().equals(driver.getId()));
        drivers.add(driver);
        db.saveDriver(driver);
    }

    public void requestRide(Rider rider,String src,String dest,double distance,FareStrategy fareStrategy) {
        if (rider == null || src == null || dest == null || distance <= 0) {
            throw new IllegalArgumentException("Invalid Ride Request");
        }

        Driver matched = dms.matchDriver(new ArrayList<>(drivers));
        if (matched == null) {
            notifier.notifyRider(rider, "No Drivers available right now.! Please try later");
            return;
        }

        Ride ride = new Ride.RideBuilder()
                .setRideId()
                .setRider(rider)
                .setDriver(matched)
                .setSource(src)
                .setDest(dest)
                .setDistance(distance)
                .setFareStrategy(fareStrategy)
                .build();

        rides.add(ride);
        db.saveRide(ride);
        matched.setAvailable(false);
        notifier.notifyRider(rider, "Ride : " + ride.getRideId() + " confirmed with " + matched.getName() + ". Fare: " + ride.getFare());
        notifier.notifyDriver(matched, "New " + ride.getRideId() + " ride assigned: " + rider.getName() + " from " + src + " to " + dest);

        /** - javadoc comment describes the method
         * Complete ride by id. Uses provided payment factory data objects for robust payment creation.
         *
         * @param rideId   ride id
         * @param method   "card" | "upi" | "cash"
         * @param cardData optional card data (for card)
         * @param upiData  optional upi data (for upi)
         */
    }
        public void completeRide(String rideID,String method,CardPaymentFactory.CardPaymentsData cardData,UPIPaymentFactory.UPIPaymentData upiData){
            Optional<Ride> opt=rides.stream().filter(r->r.getRideId().equals(rideID)).findFirst();
            if(opt.isEmpty()){
                System.err.println("Ride Not Found: "+rideID);
                return;
            }
            Ride ride=opt.get();
            if(ride.getStatus()==RideStatus.COMPLETED){
                System.err.println("Ride already completed: "+rideID);
                return;
            }

            PaymentStrategy psr=null;
            try{
                if("card".equalsIgnoreCase(method)){
                    if(cardData==null) throw new IllegalArgumentException("cardData required for card payment");
                    psr=CardPaymentFactory.createCardpayment(cardData);
                }
                else if("upi".equalsIgnoreCase(method)){
                    if(upiData==null) throw new IllegalArgumentException("upiData required for upi payment");
                    psr=UPIPaymentFactory.createUPIPayment(upiData);
                } else if ("cash".equalsIgnoreCase(method)) {
                    psr=new strategy.payment.CashPayment();
                }else{
                    throw new IllegalArgumentException("Unsupported payment method: "+method);
                }
            } catch (Exception e) {
                System.err.println("Payment initialization failed: "+e.getMessage());
                notifier.notifyRider(ride.getRider(),"Payment failed "+e.getMessage());
                return;
            }
            double amt=ride.getFare();
            try{
                psr.pay(amt);
                ride.setStatus(RideStatus.COMPLETED);
                ride.getDriver().setAvailable(true);
                db.saveRide(ride);
                notifier.notifyRider(ride.getRider(), "Ride completed. Paid: " + amt);
                notifier.notifyDriver(ride.getDriver(), "Ride completed. You received: " + amt);
            }catch (Exception e){
                System.err.println("Payment failed "+e.getMessage());
                notifier.notifyRider(ride.getRider(),"Payment failed : "+e.getMessage());
            }
        }
        public List<Ride> getAllRides(){
            return  new ArrayList<>(rides);
        }

        public Optional<Ride> getRideById(String id){
            return rides.stream().filter(r-> r.getRideId().equals(id)).findFirst();
        }
        public List<Driver> getAvailableDrivers(){
            return new ArrayList<>(drivers);
        }
}


//
//public class RideService {
//    private List<Rider> riders=new ArrayList<>();
//    private List<Driver> drivers=new ArrayList<>();
//    private List<Ride> rides=new ArrayList<>();
//
//    private NotificationService nfs=new NotificationService();
//    private DriverMatchingStrategy dms;
//
//    public RideService(DriverMatchingStrategy dms){
//        this.dms=dms;
//    }
//
//    public void addRider(Rider rider) {
//        riders.add(rider);
//    }
//
//    public void addDriver(Driver driver) {
//        drivers.add(driver);
//    }
//    public void requestRide(Rider rider,String src,String dest,double distance,FareStrategy fsr){
//
//        List<Driver> availableDrivers=drivers.stream()
//                .filter(Driver::isAvailable)
//                .toList();
//
//        Driver matchedDriver=dms.matchDriver(rider,availableDrivers);
//        if(matchedDriver==null){
//            System.out.println("No drivers available currently! for rider: "+rider.getName());
//            return;
//        }
//
//        matchedDriver.setAvailable(false);
//
//        String rideId= UUID.randomUUID().toString();
//        Ride ride=new Ride.RideBuilder()
//                .setRider(rider)
//                .setDriver(matchedDriver)
//                .setDistance(14.3)
//                .setSource("MG Road")
//                .setDest("Airport")
//                .setFareStrategy(fsr)
//                .build();
//
//
//        rides.add(ride);
//
//        nfs.notify(rider,"Ride Booked ! Ride Id:"+rideId+" with Driver "+matchedDriver.getName()+" of Fare: "+ride.getFare());
//        nfs.notify(matchedDriver,"New Ride Assigned! Ride Id:"+rideId+" Pick rider "+rider.getName()+" at "+src+" dropping at "+dest);
//    }
//
//    public void completeRide(Ride ride,String typeofPayment,String carrier,String payid){
//        ride.completeRide();
//        Driver dr=ride.getDriver();
//        dr.setAvailable(true);
//        nfs.notify(ride.getRider(),"Ride Completed ! Ride Id:"+ride.getRideId()+" with Driver "+ride.getDriver().getName()+"Pay Fare: "+ride.getFare());
//        nfs.notify(dr,"Ride Completed! Ride Id:"+ride.getRideId()+" Recieve amount of Rs."+ride.getFare());
//
//        PaymentStrategy paystr;
//        switch (typeofPayment.toLowerCase()){
//            case "card" -> paystr= CardPaymentFactory.createCardpayment(carrier,payid);
//            case "upi" -> paystr= UPIPaymentFactory.createUPIPayment(carrier,payid);
//            case "cash"-> paystr=new CashPayment();
//            default -> throw new IllegalArgumentException("Unsupported Payment Type: "+typeofPayment);
//        }
//
//        paystr.pay(ride.getFare());
//
//    }
//    public List<Ride> getAllRides(){
//        return rides;
//    }
//
//}
