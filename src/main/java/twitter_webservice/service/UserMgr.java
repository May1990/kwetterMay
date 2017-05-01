package twitter_webservice.service;

import twitter_webservice.doa.UsserDAO;
import twitter_webservice.domain.Userr;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Anna-May on 09/03/2017.
 */
@Stateless
public class UserMgr {
    /*
    private List<Userr> users;

    public UserMgr() {
        this.users = new List<Userr> ();
    }

    public List<Userr> getUsers() {
        return users;
    }

    public void setUsers(List<Userr> users) {
        this.users = users;
    }

    public Userr registerUser(String email, String userName, String password){
        return null;
    }

    public boolean setRole(String role){
        return false;
    }*/

    @Inject
    UsserDAO userDao_impl;

    public UserMgr() {
    }

//    @Inject
//    public UserMgr(UsserDAO userDao_impl){
//        this.userDao_impl = userDao_impl;
//    }

    public List<Userr> getUsers(){
        return userDao_impl.findAll();
    }

    public List<Userr> getFollowers(String username){
        return userDao_impl.findFollowersByUserName(username);
    }

    public List<Userr> getFollowing(String username){
        return userDao_impl.findFollowingByUserName(username);
    }

    public Userr getUserByUserName(String userName){
        return userDao_impl.findByUserName(userName);
    }

    public Userr createUser(String password, String email, String name, String userName, String biografy, String locationX, String locationY, String website){
        Userr user = new Userr(
                generateSHApassword(password),
                email,
                name,
                userName,
                biografy,
                locationX,
                locationY,
                website,
                "http://www.tutorialspoint.com/images/jsf-mini-logo.png"
        );

        return userDao_impl.create(user);
    }

    public String generateSHApassword(String password){
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
    }

    public void setUserDao_impl(UsserDAO userDao_impl){
        this.userDao_impl = userDao_impl;
    }

    public int getCountFollowing(Long id) {
        return userDao_impl.findCountFollowingByUsername(id);
    }

    public int getCountFollower(Long id) {
        return userDao_impl.findCountFollowersByUsername(id);
    }

    public boolean doesUsernameExist(String tempUserName) {
        return userDao_impl.doesUsernameExist(tempUserName);
    }

    public void adjustUser(Userr user){
        userDao_impl.edit(user);
    }
}
