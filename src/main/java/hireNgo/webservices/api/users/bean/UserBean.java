package hireNgo.webservices.api.users.bean;

import lombok.Getter;
import lombok.Setter;

@Getter // annotation permettant de ne pas avoir à ecrire nos getter
@Setter // et nos setters : plus clair et lisible
public class UserBean {
    private String lastname;
    private String firstname;
    private String pwd;
    private String email;
    private String phone;
    private String birthday;
}
