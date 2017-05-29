package twitter_webservice.service;

import twitter_webservice.backingbeans.TweetMgrBean;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Anna-May on 27/05/2017.
 */
//@Qualifier
//@Target({TYPE, METHOD, PARAMETER, FIELD})
//@Retention(RUNTIME)
//@Documented
//@interface TecLog {
//}
public class KwetterEndpointClient extends Endpoint{
    private Session session;
    private TweetMgrBean tweetBean;
    private static final Logger LOG = Logger.getLogger(KwetterEndpoint.class.getName());

    public void onOpen(final Session session, EndpointConfig config){
        this.session = session;

        this.session.addMessageHandler(new MessageHandler.Whole<String>() {
            public void onMessage(String message){
                LOG.info("!!!! retrieved: "+ session.getRequestURI().toString());
                LOG.info("!!!! retrieved: "+ message);
                tweetBean.updateOnMessage();
            }
        });
    }

    public void sendMessage(String message, Object tweetBean) throws IOException {
        this.tweetBean = (TweetMgrBean)tweetBean;
        this.session.getAsyncRemote().sendText(message);
    }

//    public void observeRegistrationActivity(@Observes RegistrationActivity someRegistrationActivity) {
//        EventBusFactory.getDefault().eventBus().publish("/onMessageEvent", "There was another registration");
//    }
//
//
//    public void hello(){
//        events.fire(new UpdateTweetsEvent("from bean " + System.currentTimeMillis()));
//    }
//
//    public void change(@Observes UpdateTweetsEvent updateTweetsEvent) {
//        LOG.info("!!!! retrieved: "+ "event fired");
//
//    }
}
