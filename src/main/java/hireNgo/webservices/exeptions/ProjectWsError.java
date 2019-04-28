package hireNgo.webservices.exeptions;

import com.coreoz.plume.jersey.errors.WsError;

public enum ProjectWsError implements WsError {
    WRONG_EMAIL,
    EMAIL_ALREADY_USED,
    WRONG_LASTNAME,
    WRONG_FIRSTNAME,
    NO_USER,
    NO_ID_SPECIFIED,
    USER_NOT_FOUND,
    NO_CAR,
    NO_EMAIL
}
