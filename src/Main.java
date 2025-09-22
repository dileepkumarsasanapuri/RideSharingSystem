import adapter.card.HDFCAdapter;
import adapter.upi.PhonePeAdapter;
import factory.CardPaymentFactory;
import factory.UPIPaymentFactory;
import model.Driver;
import model.Ride;
import model.Rider;
import observer.CompositeNotifier;
import observer.EmailNotifier;
import observer.NotificationService;
import observer.SMSNotifier;
import service.RideService;
import storage.Database;
import strategy.fare.FareStrategy;
import strategy.fare.NormalFare;
import strategy.matching.HighestRatedDrivingMatching;
import strategy.matching.NearestDriverMatching;
import strategy.payment.CardPayment;
import strategy.payment.CashPayment;
import strategy.payment.PaymentStrategy;
import strategy.fare.SurgeFare;
import strategy.payment.UpiPayment;

import java.util.Optional;

public class Main{
    public static void main(String[] args) {
        Database db=Database.getInstance();

        CompositeNotifier compositeNotifier=new CompositeNotifier();
        compositeNotifier.registerNotifier(new EmailNotifier());
        compositeNotifier.registerNotifier(new SMSNotifier());

        HighestRatedDrivingMatching matchingStrategy=new HighestRatedDrivingMatching();

        RideService rideService=new RideService(matchingStrategy,compositeNotifier,db);
        Rider r1 = new Rider("R101", "Anil", "anil@example.com", "9999999999", "Andhra Pradesh");
        Rider r2 = new Rider("R102", "Kalyan", "kalyan@example.com", "9997879999", "Andhra Pradesh");
        Rider r3 = new Rider("R103", "Kiran", "kiran@example.com", "9989739999", "Telangana");

        Driver d1 = new Driver("D201", "Amit", "amit@uber.com","8795732457","Andhra Pradesh","TS08AB1234", 4.7, true, "Swift Dzire", "TS09CD5678");
        Driver d2 = new Driver("D202", "Prem", "prem@la.com","9382798732","telangana","TS08AB5689", 4.9, true, "Swift Dzire", "TS09CD5983");

        try{
            rideService.addDriver(d1);
            rideService.addDriver(d2);
            rideService.addRider(r1);
            rideService.addRider(r2);
            rideService.addRider(r3);

            db.saveDriver(d1);
            db.saveDriver(d2);
            db.saveRider(r1);
            db.saveRider(r2);
            db.saveRider(r3);
        } catch (Exception e) {
            System.err.println("Error adding users: "+e.getMessage());
        }

        try{
            rideService.requestRide(r1,"MG Road","PVP",5.4,new NormalFare(30,10));
        } catch (Exception e) {
            System.err.println("Ride request failed: "+e.getMessage());
        }

        rideService.getAllRides().forEach(System.out::println);

        Optional<Ride> mabbe=rideService.getAllRides().stream().findFirst();
        if(mabbe.isPresent()){
            Ride ride=mabbe.get();
            try{
                CardPaymentFactory.CardPaymentsData cardData=new CardPaymentFactory.CardPaymentsData("HDFC","4235879450");
                UPIPaymentFactory.UPIPaymentData upiData=new UPIPaymentFactory.UPIPaymentData("Gpay","hdsf@ybl");

                rideService.completeRide(ride.getRideId(),"card",cardData,null);
            }catch (Exception e){
                System.err.println("Error Completing ride: "+e.getMessage());
            }
        }

        rideService.getAllRides().forEach(db::saveRide);
        System.out.println("Final Rides");
        rideService.getAllRides().forEach(System.out::println);
    }
}



//public class Main {
//    public static void main(String[] args) {
//        RideService rs=new RideService(new HighestRatedDrivingMatching());
//
//        Rider r1 = new Rider("R101", "Anil", "dileep@example.com", "9999999999", "Andhra Pradesh");
//        Rider r2 = new Rider("R102", "Pawan", "kalyan@example.com", "9997879999", "Andhra Pradesh");
//        Rider r3 = new Rider("R103", "Kiran", "kiran@example.com", "9989739999", "Telangana");
//
//        rs.addRider(r1);
//        rs.addRider(r2);
//        rs.addRider(r3);
//        Driver d1 = new Driver("D201", "Amit", "TS08AB1234", 4.7, true, "Swift Dzire", "TS09CD5678");
//        Driver d2 = new Driver("D202", "Prem", "TS08AB5689", 4.9, true, "Swift Dzire", "TS09CD5983");
//        rs.addDriver(d1);
//        rs.addDriver(d2);
//
//        PaymentStrategy hdfcCard=new HDFCAdapter("124463474");
//        PaymentStrategy phonePeUpi=new PhonePeAdapter("9897899009@ybl");
//        PaymentStrategy cash=new CashPayment();
//
//        rs.requestRide(r1,"Airport","Benz Circle",14.0,new NormalFare());
//        rs.requestRide(r2,"PVP","Kalakshetram",10,new SurgeFare());
//        rs.requestRide(r3,"PVR","KVR market",36,new SurgeFare());
//
//        System.out.println("All Rides - ONGOING");
//        rs.getAllRides().forEach(System.out::println);
//
//        rs.completeRide(rs.getAllRides().get(0),"card","hdfc","28376786");
//        rs.completeRide(rs.getAllRides().get(1),"upi","gpay","18489320859");
//
//        System.out.println("\nAll Rides - COMPLETED");
//        rs.getAllRides().forEach(System.out::println);
//
//
//    }
//}