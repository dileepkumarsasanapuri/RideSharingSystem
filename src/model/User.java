package model;

import java.io.Serializable;
import java.util.regex.Pattern;

public abstract class User implements Serializable {
    private static final long serialVersionId=1L;

   protected String id;
   protected String name;
   protected String email;
   protected String phone;
   protected String location;
   public User(String id,String name,String email,String phone,String location){
       validateId(id);
       validateName(name);
       validateEmail(email);
       validatePhone(phone);
       this.id=id;
       this.email=email;
       this.location=location;
       this.name=name;
       this.phone=phone;
   }

    private void validatePhone(String phone) {
        if(phone==null || phone.trim().isEmpty()){
            throw new IllegalArgumentException("Mobile No. is required");
        }
        String phnRegex="^[0-9]{10}$";
        if(!Pattern.matches(phnRegex,phone)){
            throw new IllegalArgumentException("Invalid mobile no.");
        }
    }

    private void validateEmail(String email) {
        if(email==null || email.trim().isEmpty()){
            throw new IllegalArgumentException("Email is required");
        }
        String emailRegex="^[A-Za-z0-9+_.-]+@(.+)$";
        if(!Pattern.matches(emailRegex,email)){
            throw new IllegalArgumentException("Invalid email id");
        }
    }

    private void validateName(String name) {
       if(name==null || name.trim().isEmpty()){
           throw new IllegalArgumentException("Name is required");
       }
    }

    private void validateId(String id) {
       if(id==null || id.trim().isEmpty()){
           throw new IllegalArgumentException("ID is required");
       }

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
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
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id='"+id+'\''+
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
