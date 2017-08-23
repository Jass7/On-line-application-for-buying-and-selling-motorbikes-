package model;

public class AdId implements java.io.Serializable {

    private int adId;
    private String regId;
    private String email;

    public AdId() {
    }

    public AdId(int adId, String regId, String email) {
        this.adId = adId;
        this.regId = regId;
        this.email = email;
    }

    public int getAdId() {
        return this.adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getRegId() {
        return this.regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof AdId)) {
            return false;
        }
        AdId castOther = (AdId) other;

        return (this.getAdId() == castOther.getAdId())
                && ((this.getRegId().equals(castOther.getRegId())) || (this.getRegId() != null && castOther.getRegId() != null && this.getRegId().equals(castOther.getRegId())))
                && ((this.getEmail().equals(castOther.getEmail())) || (this.getEmail() != null && castOther.getEmail() != null && this.getEmail().equals(castOther.getEmail())));
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getAdId();
        result = 37 * result + (getRegId() == null ? 0 : this.getRegId().hashCode());
        result = 37 * result + (getEmail() == null ? 0 : this.getEmail().hashCode());
        return result;
    }

}
