package observer;


import java.util.ArrayList;
import java.util.List;
public class NotificationService {
    private List<Notifier> obs=new ArrayList<>();

    public void addObserver(Notifier ob){
        obs.add(ob);
    }

    public void notifyAll(String msg){
        for(Notifier ob:obs){
            ob.update(msg);
        }
    }
    public void notify(Notifier dr,String msg){
        dr.update(msg);
    }


}
