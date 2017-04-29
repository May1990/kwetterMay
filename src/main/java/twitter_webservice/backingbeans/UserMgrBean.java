package twitter_webservice.backingbeans;

import twitter_webservice.domain.Userr;
import twitter_webservice.service.UserMgr;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Anna-May on 08/04/2017.
 */
@Named("userMgr")
@SessionScoped
public class UserMgrBean implements Serializable {
    @Inject
    private UserMgr userMgr;

    @ManagedProperty(value="#{selectedUser}")
    private Userr selectedUser;

    private List<Userr> following;
    private List<Userr> followers;
    private List<Userr> users;

    private int countFollowing;
    private int countFollower;

    //region getterSetter
    public UserMgr getUserMgr() {
        return userMgr;
    }

    public void setUserMgr(UserMgr userMgr) {
        this.userMgr = userMgr;
    }

    public Userr getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Userr selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<Userr> getFollowing() {
        return following;
    }

    public void setFollowing(List<Userr> following) {
        this.following = following;
    }

    public List<Userr> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Userr> followers) {
        this.followers = followers;
    }

    public List<Userr> getUsers() {
        return users;
    }

    public void setUsers(List<Userr> users) {
        this.users = users;
    }

    public int getCountFollowing() {
        return countFollowing;
    }

    public void setCountFollowing(int countFollowing) {
        this.countFollowing = countFollowing;
    }

    public int getCountFollower() {
        return countFollower;
    }

    public void setCountFollower(int countFollower) {
        this.countFollower = countFollower;
    }

    //endregion

    public String allUsers(){
        String message = "";
        this.users = userMgr.getUsers();
        if(users == null){
            message = "Internal error, geen gebruikers opgehaald.";
        }else{
            message = "Succeded!";
        }
        return message;
    }

    public String followersSelectedUser(String username){
        String message = "";
        this.followers = userMgr.getFollowers(username);
        if(followers.size() == 0){
            message = "Gebruiker heeft geen followers.";
        }else{
            message = "Succeded!";
        }
        return message;
    }

    public String followingSelectedUser(String username){
        String message = "";
        this.following = userMgr.getFollowing(username);
        if(following.size() == 0){
            message = "Gebruiker heeft geen followings.";
        }else{
            message = "Succeded!";
        }
        return message;
    }

    public String selectUserByUserName(String userName){
        selectedUser = userMgr.getUserByUserName(userName);
        String message = "";
        if(selectedUser == null){
            message = "Geen gebruiker gevonden.";
        }else{
            message = "Succeded!";
        }

        return message;
    }

    public void countFollow(){
        countFollowing = userMgr.getCountFollowing(selectedUser.getId());
        countFollower = userMgr.getCountFollower(selectedUser.getId());
    }
}
