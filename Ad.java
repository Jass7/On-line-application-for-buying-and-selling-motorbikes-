package model;

import java.util.Date;

public class Ad  implements java.io.Serializable {


     private AdId id;
     private Bike bike;
     private Date adPostDate;
     private Date adValidityDate;

    public Ad() {
        bike=new Bike();
        id=new AdId();
    }

    public Ad(AdId id, Bike bike, Date adPostDate, Date adValidityDate) {
       this.id = id;
       this.bike = bike;
       this.adPostDate = adPostDate;
       this.adValidityDate = adValidityDate;
    }
   
    public AdId getId() {
        return this.id;
    }
    
    public void setId(AdId id) {
        this.id = id;
    }
    public Bike getBike() {
        return this.bike;
    }
    
    public void setBike(Bike bike) {
        this.bike = bike;
    }
    public Date getAdPostDate() {
        return this.adPostDate;
    }
    
    public void setAdPostDate(Date adPostDate) {
        this.adPostDate = adPostDate;
    }
    public Date getAdValidityDate() {
        return this.adValidityDate;
    }
    
    public void setAdValidityDate(Date adValidityDate) {
        this.adValidityDate = adValidityDate;
    }
}


