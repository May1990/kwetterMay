package twitter_webservice.Rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import twitter_webservice.domain.Tweet;
import twitter_webservice.service.TweetMgr;
import twitter_webservice.service.UserMgr;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * Created by Anna-May on 16/03/2017.
 */

@Path("/RestServiceTweet")
@ApplicationPath("/resources")
@Stateless
public class RestTweet extends Application{
    @Inject
    TweetMgr tweetMgr;

    @Inject
    UserMgr userMgr;

    // test
    // http://localhost:8080/kwetterMay/resources/RestService/sayBagger
    @GET
    @Path("/sayBagger")
    public String getBagger(){
            return "Bagger!";
    }

    @GET
    @Path("/getTweetsByUserName/{username}") //check
    @Produces(MediaType.APPLICATION_JSON)
    public String getTweetsByUserName(@PathParam("username") String username) {
        List<Tweet> tweets = tweetMgr.getTweetsByUserName(username);
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(tweets);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GET
    @Path("/getTweets")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTweets() {
        List<Tweet> tweets = tweetMgr.getTweets();
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(tweets);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @PUT
    @Path("/createTweet/{content}/{username}") //check
    public void createTweet(@PathParam("username") String username, @PathParam("content") String content) {
        tweetMgr.createTweet(content, username);
    }

    @GET
    @Path("/getTweetsWithFollowing/{username}") //check
    @Produces(MediaType.APPLICATION_JSON)
    public String getTweetsWithFollowing(@PathParam("username") String username) {
        List<Tweet> tweets = tweetMgr.getTweetsWithFollowing(username);
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(tweets);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GET
    @Path("/getTweetCountByUsername/{username}") // check
    @Produces(MediaType.APPLICATION_JSON)
    public String getTweetCountByUserName(@PathParam("username") String username) {
        int count = tweetMgr.getTweetCountByUserName(username);
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(count);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GET
    @Path("/getTweetsOnlyFollowing/{id}") //check
    @Produces(MediaType.APPLICATION_JSON)
    public String getTweetsOnlyFollowing(@PathParam("id") Long id) {
        List<Tweet> tweets = tweetMgr.getTweetsOnlyFollowing(id);
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(tweets);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GET
    @Path("/getTweetsOnlyFollower/{id}") //check
    @Produces(MediaType.APPLICATION_JSON)
    public String getTweetsOnlyFollower(@PathParam("id") Long id) {
        List<Tweet> tweets = tweetMgr.getTweetsOnlyFollower(id);
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(tweets);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
