package observer;

import model.Driver;
import model.Rider;

public class EmailNotifier implements Notifier{

    @Override
    public void notifyRider(Rider rider, String msg){
        System.out.println("Email to "+rider.getEmail()+" "+msg);
    }

    @Override
    public void notifyDriver(Driver driver, String msg){
        System.out.println("Email to "+driver.getEmail()+" "+msg);
    }
}
