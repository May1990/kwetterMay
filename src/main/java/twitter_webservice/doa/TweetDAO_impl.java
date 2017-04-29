package twitter_webservice.doa;

import twitter_webservice.domain.Tweet;
import twitter_webservice.domain.Userr;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Anna-May on 09/03/2017.
 */
@Stateless
@Default
public class TweetDAO_impl implements TweetDAO {
    @PersistenceContext(unitName = "DefaultPU")
    EntityManager em;

    public TweetDAO_impl() {
    }

    public Tweet create(Tweet tweet) {
        em.persist(tweet);
        return tweet;
    }

    public void edit(Tweet tweet) {
        em.merge(tweet);
    }

    public Tweet find(Long id) {
        return em.find(Tweet.class, id);
    }

    public List<Tweet> findAll() {
        Query query = em.createNamedQuery("Tweet.all", Tweet.class);
        return (List<Tweet>) query.getResultList();
    }

    public List<Tweet> findByUser(Userr user) {
        List<Tweet> tweets;
        tweets = em.createNamedQuery("Tweet.tweetsByUser").setParameter("user", user).getResultList();
        return tweets;
    }

    public List<Tweet> findByUserFollow(Userr user) {
        List<Tweet> tweets = findByUser(user);
        for (Userr following: user.getFollowing()){
            tweets.addAll(findByUser(following));
        }

        Collections.sort(tweets);
        return tweets;
    }

    public Tweet findLastTweetByUser(Userr user) {
        List<Tweet> tweets = findByUser(user);
        Tweet tweet = null;
        if(tweets.size() > 0){
            Collections.sort(tweets);
            tweet = tweets.get(tweets.size()-1);
        }
        return tweet;
    }

    public int tweetCountByUserName(Userr user) {
        Object count = em.createNamedQuery("Tweet.tweetsCountByUser").setParameter("user", user).getSingleResult();
        return Integer.parseInt(count.toString());
    }

    public List<Tweet> tweetsOnlyFollowing(Long id) {
        Query query = em.createNativeQuery("SELECT a.following_ID FROM Following a WHERE Userr_ID = " + id);
        List<Object> ids = query.getResultList();
        List<Tweet> tweets = new ArrayList<Tweet>();
        for (Object i: ids){
            List<Tweet> newTweets = em.createNamedQuery("Tweet.tweetsById").setParameter("userId", i).getResultList();
            tweets.addAll(newTweets);
        }

        return tweets;
    }

    public List<Tweet> tweetsOnlyFollower(Long id) {
        Query query = em.createNativeQuery("SELECT a.followers_ID FROM Followers a WHERE Userr_ID = " + id);
        List<Object> ids = query.getResultList();
        List<Tweet> tweets = new ArrayList<Tweet>();
        for (Object i: ids){
            List<Tweet> newTweets = em.createNamedQuery("Tweet.tweetsById").setParameter("userId", i).getResultList();
            tweets.addAll(newTweets);
        }

        return tweets;
    }

    public void removeTweet(Tweet tweet) {
        em.remove(em.merge(tweet));
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}
