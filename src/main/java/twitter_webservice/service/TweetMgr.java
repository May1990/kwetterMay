package twitter_webservice.service;

import twitter_webservice.doa.TweetDAO;
import twitter_webservice.doa.UserrDAO_impl;
import twitter_webservice.doa.UsserDAO;
import twitter_webservice.domain.Tweet;
import twitter_webservice.domain.Userr;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by Anna-May on 09/03/2017.
 */
@Stateless
public class TweetMgr {

    /*
    private List<Tweet> tweets;

    public TweetMgr() {
        this.tweets = new List<Tweet>();
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public boolean removeTweet(int id){
        return true;
    }

    public void findTweetsWithTrend(String trend){

    }

    public List<Tweet> findTweetsWithUser(Userr user){
        return null;
    }

    public List<Tweet> findTweetsWithSearchword(String user){
        return null;
    }*/

    /*nope
    public HashMap<Integer, Tweet> getTenMostRecentTweetsFollowers(){

    }

    public HashMap<Integer, Tweet> getTenMostRecentTweetsFollowing(){

    }
    */
    /*
    public List<Tweet> getTwentyRecentTweets(Userr user){
        return null;
    }

    public List<Tweet> getTaggedTweets(Userr user){
        return null;
    }

    public List<Tweet> getMostRecentTweets(){
        return null;
    }

    public List<Tweet> getMoreRecentTweets(){
        return null;
    }

    public void refreshTweets(){
        // tweets uit het database halen
    }*/

    @Inject
    TweetDAO tweetDao_impl;

    @Inject
    UsserDAO userDao_impl;

    public TweetMgr() {

    }

    public Tweet createTweet(String content, String username) {
        Userr user = userDao_impl.findByUserName(username);
        Tweet tweet = new Tweet(new Date(), content, user);
        tweet = tweetDao_impl.create(tweet);

        return tweet;
    }

    public List<Tweet> getTweets(){
        return tweetDao_impl.findAll();
    }

    public List<Tweet> getTweetsByUserName(String userName){
        Userr user = userDao_impl.findByUserName(userName);
        return tweetDao_impl.findByUser(user);
    }

    public Tweet getLastTweetByUserName(String userName){
        Userr user = userDao_impl.findByUserName(userName);
        return tweetDao_impl.findLastTweetByUser(user);
    }

    public List<Tweet> getTweetsWithFollowing(String userName){
        Userr user = userDao_impl.findByUserName(userName);
        return tweetDao_impl.findByUserFollow(user);
    }

    public int getTweetCountByUserName(String userName) {
        Userr user = userDao_impl.findByUserName(userName);
        return tweetDao_impl.tweetCountByUserName(user);
    }

    public List<Tweet> getTweetsOnlyFollowing(Long id) {
        return tweetDao_impl.tweetsOnlyFollowing(id);
    }

    public List<Tweet> getTweetsOnlyFollower(Long id) {
        return tweetDao_impl.tweetsOnlyFollower(id);
    }

    public void setTweetDao_impl(TweetDAO tweetDao_impl){
        this.tweetDao_impl = tweetDao_impl;
    }

    public void setUserDao_impl(UserrDAO_impl userDao_impl) {
        this.userDao_impl = userDao_impl;
    }
}
