package twitter_webservice.backingbeans;

import twitter_webservice.domain.Tweet;
import twitter_webservice.service.TweetMgr;
import twitter_webservice.service.UserMgr;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

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
        ownTweetCount();
        userMgrSelectedUser.setSelectedUser(registerLogIn.getLogInUser());
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

    public void searchWordChanged()
    {
        tweets = tweetMgr.getTweetsByUserName(searchWord);
        for(Tweet tweet: tweets){
            tweet.refreshTimeAgo();
        }
    }

    public void addNewTweet(){
        tweetMgr.createTweet(newTweetContent, registerLogIn.getLogInUser().getUserName());
        refreshLastTweet();
    }

    public void refreshTweets(){
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
        tweets = tweetMgr.getTweetsOnlyFollower(userMgrSelectedUser.getSelectedUser().getId());
        for(Tweet tweet: tweets){
            tweet.refreshTimeAgo();
        }
    }

    public void profileTweetsFollowing(){
        tweets = tweetMgr.getTweetsOnlyFollowing(userMgrSelectedUser.getSelectedUser().getId());
        for(Tweet tweet: tweets){
            tweet.refreshTimeAgo();
        }
    }
}
