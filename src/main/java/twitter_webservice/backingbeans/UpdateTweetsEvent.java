package twitter_webservice.backingbeans;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * Created by Anna-May on 29/05/2017.
 */
//@RequestScoped
@SessionScoped
//@Stateless
public class UpdateTweetsEvent implements Serializable {
    private String message;

//    public UpdateTweetsEvent(String message) {
//        this.message = message;
//
//    }

    public String getMessage() {
        return message;
    }
}
