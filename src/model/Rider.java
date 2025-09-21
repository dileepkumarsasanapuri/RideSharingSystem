package model;

import observer.Notifier;
public class Rider extends User implements Notifier {
    public Rider(String id,String name,String email,String phone,String location){
        super(id,name,email,phone,location);
    }

    @Override
    public  void update(String msg){
        System.out.println("Rider "+name+" Notification: "+msg);
    }
}
