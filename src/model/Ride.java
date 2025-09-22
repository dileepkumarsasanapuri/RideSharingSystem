package model;

import strategy.fare.FareStrategy;
import strategy.fare.NormalFare;

import java.io.Serializable;
import java.util.UUID;

public class Ride implements Serializable {
    private static final long serialVersionUID = 1L;
    private String rideId;
    private Rider rider;
    private Driver driver;
    private double distance;
    private double fare;
    private RideStatus status;
    private String source;
    private String dest;
    private FareStrategy farestr;

    public Ride(RideBuilder builder){
        if (builder.rider == null || builder.driver == null) throw new IllegalArgumentException("rider/driver cannot be null");
        if (builder.source == null || builder.dest == null) throw new IllegalArgumentException("source/destination required");
        if (builder.distance <= 0) throw new IllegalArgumentException("distance must be > 0");

        this.distance=builder.distance;
        this.rider=builder.rider;
        this.source=builder.source;
        this.dest=builder.dest;
        this.rideId=builder.rideId != null ? builder.rideId : UUID.randomUUID().toString();
        this.driver=builder.driver;
        this.farestr=builder.farestr;

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

    public double getDistance() {
        return distance;
    }

    public void setStatus(RideStatus status) { this.status = status; }






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


        public RideBuilder setRideId(){
            this.rideId= UUID.randomUUID().toString();
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


        public Ride build(){
            if (this.rideId == null) setRideId();
            if(farestr==null) farestr=new NormalFare(20,12);

            if(rider==null || driver==null){
                throw  new IllegalStateException("Missing required fields for Ride");
            }
            return new Ride(this);
        }
    }
}
