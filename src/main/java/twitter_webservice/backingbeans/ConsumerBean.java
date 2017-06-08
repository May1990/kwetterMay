package twitter_webservice.backingbeans;

import org.json.simple.JSONObject;
import twitter_webservice.service.KwetterEndpoint;
import twitter_webservice.service.TweetMgr;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Anna-May on 26/05/2017.
 */
@MessageDriven(mappedName = "jms/kwetterQueue", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ConsumerBean implements MessageListener {
    @Inject
    private TweetMgr tweetMgr;
    @Inject
    private KwetterEndpoint endpoint;

    public ConsumerBean() {
    }

    public void onMessage(Message message){
        String userName = "";
        String content = "";
        TextMessage msg = (TextMessage) message;

        try {
            String textMsg = msg.getText();
            int indexx = textMsg.indexOf('%');
            userName = textMsg.substring(0, indexx);
            content = textMsg.substring(indexx +1 , textMsg.length());
            tweetMgr.createTweet(content, userName);

            JSONObject obj = new JSONObject();
            obj.put("from", userName);
            obj.put("data", content);

            String message_new = obj.toJSONString();
            endpoint.onTextMessage(message_new , userName);
        } catch (Exception ex) {
            Logger.getLogger(ConsumerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
