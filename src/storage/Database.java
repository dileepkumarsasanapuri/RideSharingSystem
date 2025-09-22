package storage;
import model.Driver;
import model.Ride;
import model.Rider;

import java.util.*;
import java.io.*;
public class Database {
    private static final String DB_DIR="db_data";
    private static final String DRIVERS_FILE=DB_DIR+"/drivers.ser";
    private static final String RIDERS_FILE=DB_DIR+"/riders.ser";
    private static final String RIDES_FILE=DB_DIR+"/rides.ser";

    private static Database instance;

    private Database(){
        File dir=new File(DB_DIR);
        if(!dir.exists()) dir.mkdirs();
    }

    public static synchronized Database getInstance(){
        if(instance==null) instance=new Database();
        return instance;
    }

    private void writeObject(String path,Object obj){
        try(FileOutputStream fos=new FileOutputStream(path);
            ObjectOutputStream oos=new ObjectOutputStream(fos)){
            oos.writeObject(obj);
        }
        catch (IOException e){
            System.err.println("DB write error: "+e.getMessage());
        }
    }

    private Object readObject(String path){
        File f=new File(path);
        if(!f.exists()) return null;
        try(FileInputStream fis=new FileInputStream(path);
        ObjectInputStream ois=new ObjectInputStream(fis)){
            return  ois.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            System.err.println("DB read error: "+e.getMessage());
            return null;
        }
    }
    public synchronized void saveDrivers(List<Driver> drivers){
        writeObject(DRIVERS_FILE,drivers);
    }
    public synchronized void saveRiders(List<Rider> riders){
        writeObject(RIDERS_FILE,riders);
    }
    public synchronized void saveRides(List<Ride> rides){
        writeObject(RIDES_FILE,rides);
    }

    public synchronized void saveDriver(Driver d){
        List<Driver> list=loadDrivers();
        list.removeIf(x->x.getId().equals(d.getId()));
        list.add(d);
        saveDrivers(list);
    }
    public synchronized void saveRider(Rider r){
        List<Rider> list=loadRiders();
        list.removeIf(x->x.getId().equals(r.getId()));
        list.add(r);
        saveRiders(list);
    }
    public synchronized void saveRide(Ride r){
        List<Ride> list=loadRides();
        list.removeIf(x->x.getRideId() != null && x.getRideId().equals(r.getRideId()));
        list.add(r);
        saveRides(list);
    }

    public synchronized List<Driver> loadDrivers(){
        Object o=readObject(DRIVERS_FILE);
        return o==null?new ArrayList<>():(List<Driver>) o;
    }

    public synchronized List<Rider> loadRiders(){
        Object o=readObject(RIDERS_FILE);
        return o==null? new ArrayList<>():(List<Rider>) o;
    }

    public synchronized List<Ride> loadRides(){
        Object o=readObject(RIDES_FILE);
        return o==null?new ArrayList<>():(List<Ride>) o;
    }


}
