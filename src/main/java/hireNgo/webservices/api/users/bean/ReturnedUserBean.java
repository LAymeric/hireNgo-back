package hireNgo.webservices.api.users.bean;

public class ReturnedUserBean {
    //parce que le JS n'aime pas les id trop longs
    private String id;
    private String firstname;

    public  ReturnedUserBean(Long id, String firstname){
        this.id = id.toString();
        this.firstname = firstname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
