package twitter_webservice.service;

import javax.ejb.Stateless;
import javax.websocket.Session;
import java.util.logging.Logger;


/**
 * Created by Anna-May on 27/05/2017.
 */
@Stateless
//@RequestScoped
public class KwetterEndpointClient{ //extends Endpoint{
    private Session session;
    private static final Logger LOG = Logger.getLogger(KwetterEndpoint.class.getName());

//    Weld weld = new Weld();
//    final WeldContainer container = weld.initialize();
//    RequestContext requestContext= container.instance().select(RequestContext.class, UnboundLiteral.INSTANCE).get();

//    @Inject
//    @Notify
//    private EventUpdateHandler eventUpdateHandler;
//
//    @Inject
//    private UpdateTweetsEvent updateTweetsEvent;
//
//    TweetMgrBean tw;
//    PartialViewContext pw;
//
//    public void setval(TweetMgrBean bean, PartialViewContext x){
//        tw = bean;
//        pw = x;
//    }
////    private final PushServlet pushServlet = new PushServlet();
////
//    public void onOpen(final Session session, EndpointConfig config){
//        this.session = session;
////        this.eventUpdateHandler = eventUpdateHandler;
////        this.updateTweetsEvent = updateTweetsEvent;
//
//        this.session.addMessageHandler(new MessageHandler.Whole<String>() {
//            public void onMessage(String message){
//                LOG.info("!!!! retrieved: "+ session.getRequestURI().toString());
//                LOG.info("!!!! retrieved: "+ message);
////                try{
////                    tw.updateOnMessage();
//////                    eventUpdateHandler.handleEvent(updateTweetsEvent);
////                }catch (Exception ex){
////                    Logger.getLogger(KwetterEndpoint.class.getName()).log(Level.SEVERE, null, ex);
////                }
////                try{
////                    EventBusFactory.getDefault().eventBus().publish("/onMessageEvent", "There was another registration");
////                }catch (Exception ex){
////                    Logger.getLogger(KwetterEndpoint.class.getName()).log(Level.SEVERE, null, ex);
////                }
//            }
//        });
//    }

//    public void sendMessage(String message) throws IOException {
//        this.session.getAsyncRemote().sendText(message);
//    }

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
