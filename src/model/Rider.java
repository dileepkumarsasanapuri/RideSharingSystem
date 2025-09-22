package model;


import java.io.Serializable;

public class Rider extends User implements Serializable {
    public Rider(String id,String name,String email,String phone,String location){
        super(id,name,email,phone,location);
    }

    @Override
    public String toString() {
        return "Rider{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}
