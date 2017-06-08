package twitter_webservice.backingbeans;

import twitter_webservice.domain.Tweet;
import twitter_webservice.service.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
/**
 * Created by Anna-May on 08/04/2017.
 */
@Named("tweetMgr")
@SessionScoped
public class TweetMgrBean implements Serializable {
    private String searchWord;
    private String newTweetContent;
    private List<Tweet> tweets;
    private Tweet lastTweet;
    private int tweetCount;
    private String tweetsGroupSelect;

    private WebSocketContainer container;

    @Inject
    private KwetterEndpointClient endpointClient;
    private Session connectToServer;
//    @Inject
//    Event<UpdateTweetsEvent> events;

    @Inject
    private UserMgr userMgr;
    @Inject
    private TweetMgr tweetMgr;

    @Inject
    private RegisterLogInBean registerLogIn;
    @Inject
    private UserMgrBean userMgrSelectedUser;

    @PostConstruct
    public void init(){
        tweets = tweetMgr.getTweetsWithFollowing(registerLogIn.getLogInUser().getUserName());
        for(Tweet tweet: tweets){
            tweet.refreshTimeAgo();
        }
        registerLogIn.refreshSizeFollow();
        refreshLastTweet();
//        try {
//            //endpointClient.setval(this, FacesContext.getCurrentInstance().getPartialViewContext());
//            //endpointClient = new KwetterEndpointClient();
//            this.container = ContainerProvider.getWebSocketContainer();
//            // open connection
//            connectToServer = this.container.connectToServer(this.endpointClient, new URI("ws://localhost:8080/kwetterMay/kwetterWeb/" + this.registerLogIn.getLogInUser().getUserName()));
//        } catch (Exception ex) {
//            Logger.getLogger(TweetMgrBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    //region getterSetter
    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getNewTweetContent() {
        return newTweetContent;
    }

    public void setNewTweetContent(String newTweetContent) {
        this.newTweetContent = newTweetContent;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public UserMgr getUserMgr() {
        return userMgr;
    }

    public void setUserMgr(UserMgr userMgr) {
        this.userMgr = userMgr;
    }

    public TweetMgr getTweetMgr() {
        return tweetMgr;
    }

    public void setTweetMgr(TweetMgr tweetMgr) {
        this.tweetMgr = tweetMgr;
    }

    public RegisterLogInBean getRegisterLogIn() {
        return registerLogIn;
    }

    public void setRegisterLogIn(RegisterLogInBean registerLogIn) {
        this.registerLogIn = registerLogIn;
    }

    public void setUserMgrSelectedUser(UserMgrBean userMgrSelectedUser) {
        this.userMgrSelectedUser = userMgrSelectedUser;
    }

    public Tweet getLastTweet() {
        return lastTweet;
    }

    public void setLastTweet(Tweet lastTweet) {
        this.lastTweet = lastTweet;
    }

    public int getTweetCount() {
        return tweetCount;
    }

    public void setTweetCount(int tweetCount) {
        this.tweetCount = tweetCount;
    }
    //endregion

    public void refreshProfile(){
        ownTweetCount();
        userMgrSelectedUser.setSelectedUser(registerLogIn.getLogInUser());
        userMgrSelectedUser.refreshAtrUser();
    }

    public void searchWordChanged(){
        tweets = tweetMgr.getTweetsByUserName(searchWord);
        for(Tweet tweet: tweets){
            tweet.refreshTimeAgo();
        }
    }

    public void addNewTweet(){
        tweetMgr.createTweet(newTweetContent, registerLogIn.getLogInUser().getUserName());
        refreshLastTweet();
//        try {
//            this.endpointClient.sendMessage(newTweetContent);
//            //webSocket.sendTweetToServer(registerLogIn.getLogInUser().getUserName(), newTweetContent, this);
//        } catch (Exception ex) {
//            Logger.getLogger(TweetMgrBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void refreshTweets(Object event){
        tweetsGroupSelect = "withFollowing";
        if(userMgrSelectedUser.getSelectedUser() != null){
            tweets = tweetMgr.getTweetsWithFollowing(userMgrSelectedUser.getSelectedUser().getUserName());

            for(Tweet tweet: tweets){
                tweet.refreshTimeAgo();
            }
        }
    }

    public void refreshOwnTweets(){
        tweetsGroupSelect = "ownTweets";
        if(userMgrSelectedUser.getSelectedUser() != null){
            tweets = tweetMgr.getTweetsByUserName(userMgrSelectedUser.getSelectedUser().getUserName());

            for(Tweet tweet: tweets){
                tweet.refreshTimeAgo();
            }
        }
    }

    public void refreshLastTweet(){
        lastTweet = tweetMgr.getLastTweetByUserName(registerLogIn.getLogInUser().getUserName());
        lastTweet.refreshTimeAgo();
    }

    public void ownTweetCount(){
        tweetCount = tweetMgr.getTweetCountByUserName(registerLogIn.getLogInUser().getUserName());
    }

    public void profileTweetCount(){
        tweetCount = tweetMgr.getTweetCountByUserName(userMgrSelectedUser.getSelectedUser().getUserName());
    }

    public void profileTweetsFollower(){
        tweetsGroupSelect = "follower";
        tweets = tweetMgr.getTweetsOnlyFollower(userMgrSelectedUser.getSelectedUser().getId());
        for(Tweet tweet: tweets){
            tweet.refreshTimeAgo();
        }
    }

    public void profileTweetsFollowing(){
        tweetsGroupSelect = "following";
        tweets = tweetMgr.getTweetsOnlyFollowing(userMgrSelectedUser.getSelectedUser().getId());
        for(Tweet tweet: tweets){
            tweet.refreshTimeAgo();
        }
    }

//    public void updateTweets(@Observes UpdateTweetsEvent updateTweetsEvent){
//        Logger.getAnonymousLogger().info("Received Event: "+ updateTweetsEvent.getClass().getName());
//        Logger.getLogger(TweetMgrBean.class.getName()).info("user past... "+ registerLogIn.getLogInUser().getUserName());
//        System.out.println("HelloEvent: " + updateTweetsEvent);
//
//        if(tweetsGroupSelect == "follower"){
//            profileTweetsFollower();
//        }else if( tweetsGroupSelect == "withFollowing"){
//            refreshTweets(null);
//        }else if( tweetsGroupSelect == "following"){
//            profileTweetsFollowing();
//        }else if( tweetsGroupSelect == "ownTweets"){
//            refreshOwnTweets();
//        }
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        Logger.getLogger(TweetMgrBean.class.getName()).info("user past... "+ registerLogIn.getLogInUser().getUserName());
//        if(tweetsGroupSelect!=null){
//            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(":profileTweetForm");
//        }
//    }

//    public void updateOnMessage(){
//        events.fire(new UpdateTweetsEvent("from bean " + System.currentTimeMillis()));
//    }
//@Observes UpdateTweetsEvent updateTweetsEvent


//    private PushServlet psuh;
//    @Inject
//    @Notify
//    private EventUpdateHandler eventUpdateHandler;
//
//    @Inject
//    private UpdateTweetsEvent updateTweetsEvent;

    public void updateOnMessage(){
        //Logger.getAnonymousLogger().info("Received Event: "+ updateTweetsEvent.getClass().getName());
        Logger.getLogger(TweetMgrBean.class.getName()).info("user past... "+ registerLogIn.getLogInUser().getUserName());
        //System.out.println("HelloEvent: " + updateTweetsEvent);

        if(tweetsGroupSelect == "follower"){
            profileTweetsFollower();
        }else if( tweetsGroupSelect == "withFollowing"){
            refreshTweets(null);
        }else if( tweetsGroupSelect == "following"){
            profileTweetsFollowing();
        }else if( tweetsGroupSelect == "ownTweets"){
            refreshOwnTweets();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Logger.getLogger(TweetMgrBean.class.getName()).info("user past... "+ registerLogIn.getLogInUser().getUserName());
        if(tweetsGroupSelect!=null){
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(":profileTweetForm");
        }


        //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(":tableForm");
    }
}
