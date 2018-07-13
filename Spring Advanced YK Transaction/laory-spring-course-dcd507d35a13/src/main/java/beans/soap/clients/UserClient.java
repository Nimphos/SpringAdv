/*
package beans.soap.clients;

import beans.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
public class UserClient {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public User getUserByid(int id){

        User result = (User) webServiceTemplate.marshalSendAndReceive(id);

        return result;
    }
}
*/
