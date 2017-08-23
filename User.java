package model;

import java.util.HashSet;
import java.util.Set;

public class User  implements java.io.Serializable {

     private String email;
     private String fullName;
     private String password;
     private String tellNo;
     private String role;
     private Set<Bike> bikes = new HashSet<Bike>(0);

    public User() {
    }
	
    public User(String email, String fullName, String password, String role) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
    }
    
    public User(String email, String fullName, String password, String tellNo, String role, Set<Bike> bikes) {
       this.email = email;
       this.fullName = fullName;
       this.password = password;
       this.tellNo = tellNo;
       this.role = role;
       this.bikes = bikes;
    }
   
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getTellNo() {
        return this.tellNo;
    }
    
    public void setTellNo(String tellNo) {
        this.tellNo = tellNo;
    }
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    public Set<Bike> getBikes() {
        return this.bikes;
    }
    
    public void setBikes(Set<Bike> bikes) {
        this.bikes = bikes;
    }
}


