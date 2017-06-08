package twitter_webservice.service;

import twitter_webservice.domain.Userr;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Anna-May on 27/05/2017.
 */
@Stateless
@ServerEndpoint("/kwetterWeb/{username}")
public class KwetterEndpoint {
    private static final Logger LOG = Logger.getLogger(KwetterEndpoint.class.getName());
    public static Map<String, Session> Sessions = new HashMap<String,Session>();

    @Inject
    private UserMgr userMgr;

    @OnOpen
    public void onCreateSession(Session session, @PathParam("username") String username){
        Sessions.put(username, session);
        LOG.info(username + "opened the connection");
    }

    @OnMessage
    public String onTextMessage(String message, @PathParam("username") String username){
        Userr user = userMgr.getUserByUserName(username);
        List<Userr> users = userMgr.getFollowers(username);
//        users.addAll(userMgr.getFollowing(username));
        LOG.info(username + " message: " + message);

        for (Userr follow: users) {
            String userName = follow.getUserName();
            if (Sessions.containsKey(userName)){
                try {
                    Sessions.get(userName).getAsyncRemote().sendText(message);
                } catch (Exception ex) {
                    Logger.getLogger(KwetterEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return message;
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        Sessions.remove(username);
        LOG.info("Connection has been closed by " + username);
    }
}
