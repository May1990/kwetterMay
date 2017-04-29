package twitter_webservice.doa;


import twitter_webservice.domain.Tweet;
import twitter_webservice.domain.Userr;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Anna-May on 09/03/2017.
 */
@Local
public interface TweetDAO {
    Tweet create(Tweet tweet);
    void edit(Tweet tweet);
    Tweet find(Long id);
    List<Tweet> findAll();
    List<Tweet> findByUser(Userr user);
    void removeTweet(Tweet tweet);
    List<Tweet> findByUserFollow(Userr user);
    Tweet findLastTweetByUser(Userr user);
    int tweetCountByUserName(Userr userName);
    List<Tweet> tweetsOnlyFollowing(Long id);
    List<Tweet> tweetsOnlyFollower(Long id);
}
