package model;

import observer.Notifier;

public class Driver extends User implements Notifier {
    private String licenseNo;
    private double rating;
    private boolean available;
    private String vehicleNo;
    private String vehicleModel;
    public Driver(String id,String name,String licenseNo,double rating,boolean available,String vehicleModel,String vehicleNo){
        super(id,name,null,null,null);
        this.licenseNo=licenseNo;
        this.rating=rating;
        this.available=available;
        this.vehicleModel=vehicleModel;
        this.vehicleNo=vehicleNo;
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
    public String toString(){
        return "Driver: "+name+"of rating: "+rating+" , "+vehicleModel+" - "+vehicleNo+" ";
    }
    @Override
    public void update(String msg) {
        System.out.println("Driver " + name + " got notification: " + msg);
    }
}

