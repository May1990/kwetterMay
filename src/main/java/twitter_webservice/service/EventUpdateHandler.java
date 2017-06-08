package twitter_webservice.service;

import twitter_webservice.backingbeans.UpdateTweetsEvent;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Anna-May on 31/05/2017.
 */
@Notify
//@RequestScoped
//@Stateless
@SessionScoped
public class EventUpdateHandler implements Serializable {
    @Inject
    Event<UpdateTweetsEvent> events;

    public void handleEvent(UpdateTweetsEvent updateTweetsEvent) {
        System.out.println("Firing Event");
        events.fire(updateTweetsEvent);
    }
}
