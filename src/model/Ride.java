package model;

import strategy.fare.FareStrategy;
import strategy.fare.NormalFare;
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
    public Ride(RideBuilder builder){
        this.distance=builder.distance;
        this.rider=builder.rider;
        this.source=builder.source;
        this.dest=builder.dest;
        this.rideId=builder.rideId;
        this.driver=builder.driver;
        this.farestr=builder.farestr;
        this.paystr=builder.paystr;
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

    public PaymentStrategy getPaystr() {
        return paystr;
    }

    @Override
    public String toString() {
        return "Ride {" +
                "rider=" + rider.getName() +
                ", driver=" + driver.getName() +
                ", source='" + source + '\'' +
                ", destination='" + dest + '\'' +
                ", fare=" + fare +
                ", status=" + status +
                '}';
    }

    public static class RideBuilder{
        private String rideId;
        private Rider rider;
        private Driver driver;
        private double distance;
        private String source;
        private String dest;
        private FareStrategy farestr;
        private PaymentStrategy paystr;

        public RideBuilder setRiderId(String riderId){
            this.rideId=riderId;
            return this;
        }
        public RideBuilder setRider(Rider rider){
            this.rider=rider;
            return this;
        }
        public RideBuilder setDriver(Driver driver){
            this.driver=driver;
            return this;
        }
        public RideBuilder setDistance(double distance) {
            this.distance = distance;
            return this;
        }

        public RideBuilder setSource(String source) {
            this.source = source;
            return this;
        }

        public RideBuilder setDest(String dest) {
            this.dest = dest;
            return this;
        }

        public RideBuilder setFareStrategy(FareStrategy farestr) {
            this.farestr = farestr;
            return this;
        }

        public RideBuilder setPaymentStrategy(PaymentStrategy paystr) {
            this.paystr = paystr;
            return this;
        }
        public Ride build(){
            if(farestr==null) farestr=new NormalFare();
            if(rider==null || driver==null ||paystr==null){
                throw  new IllegalStateException("Missing required fields for Ride");
            }
            return new Ride(this);
        }
    }
}
