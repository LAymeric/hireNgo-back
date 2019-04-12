package hireNgo.webservices.exeptions;

import com.coreoz.plume.jersey.errors.WsError;

public enum ProjectWsError implements WsError {
    WRONG_EMAIL,
    WRONG_LASTNAME,
    WRONG_FIRSTNAME,
    NO_USER
}
