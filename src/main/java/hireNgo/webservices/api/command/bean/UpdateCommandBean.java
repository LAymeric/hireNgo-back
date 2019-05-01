package hireNgo.webservices.api.command.bean;

import hireNgo.webservices.api.services.bean.ServiceBean;

import java.util.List;

public class UpdateCommandBean {

    private String idUser;
    private String idCommand;
    private List<ServiceBean> services;

    public String getIdCommand() {
        return idCommand;
    }

    public void setIdCommand(String idCommand) {
        this.idCommand = idCommand;
    }

    public List<ServiceBean> getServices() {
        return services;
    }

    public void setServices(List<ServiceBean> services) {
        this.services = services;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
