package model;

public class Enquiry  implements java.io.Serializable {

     private Integer enqId;
     private String enqName;
     private String enqEmail;
     private String enqPhoneNo;
     private String enqType;
     private String enqMsg;

    public Enquiry() {
    }

    public Enquiry(String enqName, String enqEmail, String enqPhoneNo, String enqType, String enqMsg) {
       this.enqName = enqName;
       this.enqEmail = enqEmail;
       this.enqPhoneNo = enqPhoneNo;
       this.enqType = enqType;
       this.enqMsg = enqMsg;
    }
   
    public Integer getEnqId() {
        return this.enqId;
    }
    
    public void setEnqId(Integer enqId) {
        this.enqId = enqId;
    }
    public String getEnqName() {
        return this.enqName;
    }
    
    public void setEnqName(String enqName) {
        this.enqName = enqName;
    }
    public String getEnqEmail() {
        return this.enqEmail;
    }
    
    public void setEnqEmail(String enqEmail) {
        this.enqEmail = enqEmail;
    }
    public String getEnqPhoneNo() {
        return this.enqPhoneNo;
    }
    
    public void setEnqPhoneNo(String enqPhoneNo) {
        this.enqPhoneNo = enqPhoneNo;
    }
    public String getEnqType() {
        return this.enqType;
    }
    
    public void setEnqType(String enqType) {
        this.enqType = enqType;
    }
    public String getEnqMsg() {
        return this.enqMsg;
    }
    
    public void setEnqMsg(String enqMsg) {
        this.enqMsg = enqMsg;
    }

    public void error(String couldnt_roll_back_transaction, RuntimeException rbe) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}


