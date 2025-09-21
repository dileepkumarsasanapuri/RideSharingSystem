import model.Driver;
import model.Ride;
import model.Rider;
import observer.NotificationService;
import service.RideService;
import strategy.fare.FareStrategy;
import strategy.fare.NormalFare;
import strategy.matching.HighestRatedDrivingMatching;
import strategy.payment.CashPayment;
import strategy.payment.PaymentStrategy;
import strategy.fare.SurgeFare;
import strategy.payment.UpiPayment;

public class Main {
    public static void main(String[] args) {
        RideService rs=new RideService(new HighestRatedDrivingMatching());

        Rider r1 = new Rider("R101", "Dileep Kumar", "dileep@example.com", "9999999999", "Andhra Pradesh");
        Rider r2 = new Rider("R102", "Pawan kalyan", "kalyan@example.com", "9997879999", "Andhra Pradesh");
        rs.addRider(r1);
        rs.addRider(r2);
        Driver d1 = new Driver("D201", "Amit Singh", "TS08AB1234", 4.7, true, "Swift Dzire", "TS09CD5678");
        Driver d2 = new Driver("D202", "Prabhas", "TS08AB5689", 4.9, true, "Swift Dzire", "TS09CD5983");
        rs.addDriver(d1);
        rs.addDriver(d2);

        rs.requestRide(r1,"Airport","Benz Circle",14.0,new NormalFare(),new UpiPayment("9999999999@ybl"));
        rs.requestRide(r2,"PVP","Kalakshetram",10,new SurgeFare(),new CashPayment());

        System.out.println("All Rides - ONGOING");
        rs.getAllRides().forEach(System.out::println);

        for(Ride ride:rs.getAllRides()){
            rs.completeRide(ride);
        }

    }
}