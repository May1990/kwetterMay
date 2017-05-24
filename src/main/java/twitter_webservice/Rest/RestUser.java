package twitter_webservice.Rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@Path("/RestServiceUser")
@ApplicationPath("/resources")
@Stateless
public class RestUser extends Application{
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


//    @GET
//    @Path("/getUsers")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getUsers() {
//        List<Userr> users = userMgr.getUsers();
//        String result = null;
//        try {
//            result = new ObjectMapper().writeValueAsString(users);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    @GET
    @Path("/getFollowers/{username}") //check
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
    @Path("/getFollowing/{username}") //check
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
    @Path("/getUserByUserName/{username}") // check
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
    @Path("/createUser/{password}/{email}/{name}/{username}/{biografy}/{locationX}/{locationY}/{website}") //check
    public void createUser(@PathParam("username") String username, @PathParam("password") String password,
                                   @PathParam("email") String email,
                                    @PathParam("biografy") String biografy,
                                   @PathParam("locationX") String locationX,
                                   @PathParam("locationY") String locationY,
                                   @PathParam("website") String website, @PathParam("name") String name) {
        userMgr.createUser(password,email,name, username, biografy, locationX, locationY, website);
    }

    @GET
    @Path("/getCountFollowing/{id}") // check
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountFollowing(@PathParam("id") Long id) {
        int count = userMgr.getCountFollowing(id);
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(count);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GET
    @Path("/getCountFollower/{id}") // check
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountFollower(@PathParam("id") Long id) {
        int count = userMgr.getCountFollower(id);
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(count);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GET
    @Path("/doesUserNameExist/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String doesUserNameExist(@PathParam("username") String username) {
        boolean succes = userMgr.doesUsernameExist(username);
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(succes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @PUT
    @Path("/adjustUser/{email}/{name}/{username}/{biografy}/{pictureUrl}/{website}")
    public void adjustUserWithUserName(@PathParam("username") String username,
                           @PathParam("email") String email,
                           @PathParam("biografy") String biografy,
                           @PathParam("website") String website,
                           @PathParam("pictureUrl") String pictureUrl,
                           @PathParam("name") String name) {
        Userr user = userMgr.getUserByUserName(username);
        user.setEmail(email);
        user.setBiografy(biografy);
        user.setWebsite(website);
        user.setPictureUrl(pictureUrl);
        user.setName(name);
        userMgr.adjustUser(user);
    }

    @PUT
    @Path("/adjustUserName/{username}/{oldUsername}")
    public void adjustUsername(@PathParam("username") String username,
                                       @PathParam("oldUsername") String oldUsername) {
        Userr user = userMgr.getUserByUserName(oldUsername);
        user.setUserName(username);
        userMgr.adjustUser(user);
    }
}
