package hireNgo.webservices.api.services.bean;

import java.util.List;

public class ServicesBean {
    private String userEmail;
    private List<String> serviceIds;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<String> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<String> serviceIds) {
        this.serviceIds = serviceIds;
    }
}
