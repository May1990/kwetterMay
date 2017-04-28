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

    @Inject
    private UserMgr userMgr;
    @Inject
    private TweetMgr tweetMgr;

    @Inject
    private RegisterLogInBean registerLogIn;
    @Inject
    UserMgrBean userMgrSelectedUser;

    @PostConstruct
    public void init(){
        tweets = tweetMgr.getTweetsWithFollowing(registerLogIn.getLogInUser().getUserName());
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
    //endregion

    public void searchWordChanged()
    {
        tweets = tweetMgr.getTweetsByUserName(searchWord);
    }

    public void addNewTweet(){
        tweetMgr.createTweet(newTweetContent, registerLogIn.getLogInUser().getUserName());
    }

    public void refreshTweets(){
        if(searchWord == ""){
            tweets = tweetMgr.getTweetsByUserName(registerLogIn.getLogInUser().getUserName());
        }else{
            tweets = tweetMgr.getTweetsByUserName(searchWord);
        }
    }
}
