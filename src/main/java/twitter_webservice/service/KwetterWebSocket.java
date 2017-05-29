package twitter_webservice.service;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Anna-May on 27/05/2017.
 */
public class KwetterWebSocket {
    private WebSocketContainer container;
    private KwetterEndpointClient endpointClient;
    private Session connectToServer;

    public KwetterWebSocket() {
        this.container = ContainerProvider.getWebSocketContainer();
        this.endpointClient = new KwetterEndpointClient();
    }

    public void openConnection(String username) throws URISyntaxException, IOException, DeploymentException{
        connectToServer = this.container.connectToServer(this.endpointClient, new URI("ws://localhost:8080/kwetterMay/kwetterWeb/" + username));
    }

    public void sendTweetToServer(String username, String tweetContent, Object tweetBean) throws URISyntaxException, IOException, DeploymentException {
        this.endpointClient.sendMessage(tweetContent, tweetBean);
    }
}
