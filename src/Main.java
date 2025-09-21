import model.Driver;
import model.Ride;
import model.Rider;
import observer.NotificationService;
import service.RideService;
import strategy.fare.FareStrategy;
import strategy.payment.PaymentStrategy;
import strategy.fare.SurgeFare;
import strategy.payment.UpiPayment;

public class Main {
    public static void main(String[] args) {

        Rider rider = new Rider("R101", "Dileep Kumar", "dileep@example.com", "9999999999", "Andhra Pradesh");
        Driver driver = new Driver("D201", "Amit Singh", "TS08AB1234", 4.7, true, "Swift Dzire", "TS09CD5678");

        NotificationService nfs=new NotificationService();
        nfs.addObserver(rider);

        FareStrategy fsr=new SurgeFare();
        PaymentStrategy psr=new UpiPayment("dileep@upi");

        RideService rs=new RideService(nfs);
        Ride ride=rs.bookRide(rider,driver,12.5,fsr,psr);
        rs.startRide(ride);
        rs.completeRide(ride);

    }
}