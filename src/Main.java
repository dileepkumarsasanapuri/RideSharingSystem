import adapter.card.HDFCAdapter;
import adapter.upi.PhonePeAdapter;
import model.Driver;
import model.Ride;
import model.Rider;
import observer.NotificationService;
import service.RideService;
import strategy.fare.FareStrategy;
import strategy.fare.NormalFare;
import strategy.matching.HighestRatedDrivingMatching;
import strategy.payment.CardPayment;
import strategy.payment.CashPayment;
import strategy.payment.PaymentStrategy;
import strategy.fare.SurgeFare;
import strategy.payment.UpiPayment;

public class Main {
    public static void main(String[] args) {
        RideService rs=new RideService(new HighestRatedDrivingMatching());

        Rider r1 = new Rider("R101", "Anil", "dileep@example.com", "9999999999", "Andhra Pradesh");
        Rider r2 = new Rider("R102", "Pawan", "kalyan@example.com", "9997879999", "Andhra Pradesh");
        Rider r3 = new Rider("R103", "Kiran", "kiran@example.com", "9989739999", "Telangana");

        rs.addRider(r1);
        rs.addRider(r2);
        rs.addRider(r3);
        Driver d1 = new Driver("D201", "Amit", "TS08AB1234", 4.7, true, "Swift Dzire", "TS09CD5678");
        Driver d2 = new Driver("D202", "Prem", "TS08AB5689", 4.9, true, "Swift Dzire", "TS09CD5983");
        rs.addDriver(d1);
        rs.addDriver(d2);

        PaymentStrategy hdfcCard=new HDFCAdapter("124463474");
        PaymentStrategy phonePeUpi=new PhonePeAdapter("9897899009@ybl");
        PaymentStrategy cash=new CashPayment();

        rs.requestRide(r1,"Airport","Benz Circle",14.0,new NormalFare(),hdfcCard);
        rs.requestRide(r2,"PVP","Kalakshetram",10,new SurgeFare(),phonePeUpi);
        rs.requestRide(r3,"PVR","KVR market",36,new SurgeFare(),cash);

        System.out.println("All Rides - ONGOING");
        rs.getAllRides().forEach(System.out::println);

        for(Ride ride:rs.getAllRides()){
            rs.completeRide(ride);
        }

    }
}