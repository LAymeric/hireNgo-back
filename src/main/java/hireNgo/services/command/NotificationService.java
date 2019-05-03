package hireNgo.services.command;

import com.sun.media.jfxmedia.logging.Logger;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import javax.inject.Inject;

public class NotificationService {

    private static final String ACCOUNT_SID = "AC27e193df9a4dda9634746415f525ece6";
    private static final String AUTH_TOKEN = "3f80dfaf271b5ef3fe26ffeebb2e92a6";

    @Inject
    public NotificationService(){

    }

    public void sendNotif(String toNumber, String messageBody){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try {
            Message.creator(
                    new com.twilio.type.PhoneNumber("+33"+toNumber.substring(1)),//on ne veut pas du 06 mais +336
                    new com.twilio.type.PhoneNumber("+33644600702"),
                    messageBody)
                    .create();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
