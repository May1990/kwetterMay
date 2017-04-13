package twitter_webservice.Rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import twitter_webservice.domain.Tweet;
import twitter_webservice.domain.Userr;
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

@Path("/RestService")
@ApplicationPath("/resources")
@Stateless
public class Rest extends Application{
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

    // tweet
    @GET
    @Path("/getTweetsByUserName/{username}")
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

    @GET
    //@RolesAllowed("regulars")
    @Path("/getUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        List<Userr> users = userMgr.getUsers();
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GET
    @Path("/getFollowers/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFollowers(@PathParam("username") String username) {
        List<Userr> users = userMgr.getFollowers(username);
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GET
    @Path("/getFollowing/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFollowing(@PathParam("username") String username) {
        List<Userr> users = userMgr.getFollowing(username);
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GET
    @Path("/getUserByUserName/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserByUserName(@PathParam("username") String username) {
        Userr userr = userMgr.getUserByUserName(username);
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(userr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @PUT
    @Path("/createUser/{password}/{email}/{name}/{username}/{biografy}/{locationX}/{locationY}/{website}")
    public void createUser(@PathParam("username") String username, @PathParam("password") String password,
                                   @PathParam("email") String email,
                                    @PathParam("biografy") String biografy,
                                   @PathParam("locationX") String locationX,
                                   @PathParam("locationY") String locationY,
                                   @PathParam("website") String website, @PathParam("name") String name) {
        userMgr.createUser(password,email,name, username, biografy, locationX, locationY, website);
    }
}
