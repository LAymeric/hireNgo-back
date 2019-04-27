package hireNgo.webservices.api.users.bean;

public class ReturnedUserBean {
    //parce que le JS n'aime pas les id trop longs
    private String email;
    private String firstname;
    private String type;
    private boolean hasFilledData;

    public  ReturnedUserBean(String email, String firstname, String type, boolean hasFilledData){
        this.email = email;
        this.firstname = firstname;
        this.type = type;
        this.hasFilledData = hasFilledData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHasFilledData() {
        return hasFilledData;
    }

    public void setHasFilledData(boolean hasFilledData) {
        this.hasFilledData = hasFilledData;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
