package model;

public abstract class User{
   protected String id;
   protected String name;
   protected String email;
   protected String phone;
   protected String location;
   public User(String id,String name,String email,String phone,String location){
       this.id=id;
       this.email=email;
       this.location=location;
       this.name=name;
       this.phone=phone;
   }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }
}
