package model;


import java.io.Serializable;
import java.util.Objects;

public class Driver extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String licenseNo;
    private double rating;
    private boolean available;
    private String vehicleNo;
    private String vehicleModel;
    public Driver(String id,String name,String email,String phone,String location,String licenseNo,double rating,boolean available,String vehicleModel,String vehicleNo){
        super(id,name,email,phone,location);
        if (vehicleNo == null || vehicleNo.trim().isEmpty()) throw new IllegalArgumentException("carNo required");
        if (rating < 0 || rating > 5) throw new IllegalArgumentException("rating must be 0-5");
        this.licenseNo=licenseNo;
        this.rating=rating;
        this.available=available;
        this.vehicleModel=vehicleModel;
        this.vehicleNo=vehicleNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public double getRating(){
        return rating;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof Driver)) return false;
        Driver driver=(Driver) o;
        return Objects.equals(getId(),driver.getId());
    }
    @Override
    public int hashCode(){
        return Objects.hash(getId());
    }
    @Override
    public String toString(){
        return "Driver: "+name+"of rating: "+rating+" , "+vehicleModel+" - "+vehicleNo+" ";
    }

}

