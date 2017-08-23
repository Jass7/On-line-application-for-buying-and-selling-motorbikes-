package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Bike  implements java.io.Serializable {

     private BikeId id;
     private User user;
     private String color;
     private String model;
     private Integer yearOfPro;
     private String tempNote;
     private String genCondition;
     private byte[] photo;
     private Date validityOfReg;
     private String city;
     private String country;
     private Integer mileage;
     private Integer price;
     private Set<Ad> ads = new HashSet<>(0);

    public Bike() {
        user=new User();
        id=new BikeId();
    }

	
    public Bike(BikeId id, User user, String color, String model, Integer yearOfPro, Date validityOfReg, Integer price) {
        this.id = id;
        this.user = user;
        this.color = color;
        this.model = model;
        this.yearOfPro = yearOfPro;
        this.validityOfReg = validityOfReg;
        this.price = price;
    }
    public Bike(BikeId id, User user, String color, String model, Integer yearOfPro, String tempNote, String genCondition, byte[] photo, Date validityOfReg, String city, String country, Integer mileage, Integer price, Set<Ad> ads) {
       this.id = id;
       this.user = user;
       this.color = color;
       this.model = model;
       this.yearOfPro = yearOfPro;
       this.tempNote = tempNote;
       this.genCondition = genCondition;
       this.photo = photo;
       this.validityOfReg = validityOfReg;
       this.city = city;
       this.country = country;
       this.mileage = mileage;
       this.price = price;
       this.ads = ads;
    }
   
    public BikeId getId() {
        return this.id;
    }
    
    public void setId(BikeId id) {
        this.id = id;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    public String getModel() {
        return this.model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    public Integer getYearOfPro() {
        return this.yearOfPro;
    }
    
    public void setYearOfPro(Integer yearOfPro) {
        this.yearOfPro = yearOfPro;
    }
    public String getTempNote() {
        return this.tempNote;
    }
    
    public void setTempNote(String tempNote) {
        this.tempNote = tempNote;
    }
    public String getGenCondition() {
        return this.genCondition;
    }
    
    public void setGenCondition(String genCondition) {
        this.genCondition = genCondition;
    }
    public byte[] getPhoto() {
        return this.photo;
    }
    
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    public Date getValidityOfReg() {
        return this.validityOfReg;
    }
    
    public void setValidityOfReg(Date validityOfReg) {
        this.validityOfReg = validityOfReg;
    }
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    public Integer getMileage() {
        return this.mileage;
    }
    
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
    public Integer getPrice() {
        return this.price;
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Set<Ad> getAds() {
        return this.ads;
    }
    
    public void setAds(Set<Ad> ads) {
        this.ads = ads;
    }
}


