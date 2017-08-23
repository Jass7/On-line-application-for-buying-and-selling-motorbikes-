package model;

public class BikeId implements java.io.Serializable {

    private String regId;
    private String email;

    public BikeId() {
    }

    public BikeId(String regId, String email) {
        this.regId = regId;
        this.email = email;
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
        if (!(other instanceof BikeId)) {
            return false;
        }
        BikeId castOther = (BikeId) other;

        return 
                ((this.getRegId().equals(castOther.getRegId())) || (this.getRegId() != null && castOther.getRegId() != null && this.getRegId().equals(castOther.getRegId())))
                && ((this.getEmail().equals(castOther.getEmail())) || (this.getEmail() != null && castOther.getEmail() != null && this.getEmail().equals(castOther.getEmail())));
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + (getRegId() == null ? 0 : this.getRegId().hashCode());
        result = 37 * result + (getEmail() == null ? 0 : this.getEmail().hashCode());
        return result;
    }
}
