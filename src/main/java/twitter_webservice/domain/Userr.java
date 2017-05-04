package twitter_webservice.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna-May on 09/03/2017.
 */
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQueries({
        @NamedQuery(name = "User.all", query = "SELECT c FROM Userr c"),
        @NamedQuery(name = "User.userByUserName", query = "SELECT c FROM Userr c WHERE c.userName = :userName"),
        @NamedQuery(name = "User.existUserName", query = "SELECT count(c.userName) FROM Userr c WHERE c.userName = :userName")
})
public class Userr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @JsonIgnore
    private String password;
    @JsonIgnore
    @ManyToMany(mappedBy = "users", cascade = CascadeType.PERSIST)
    private List<Role> roles;

    private String email;
    private String name;

    @Column(unique = true)
    private String userName;
    private String biografy;
    @JsonIgnore
    private String locationX;
    @JsonIgnore
    private String locationY;
    private String website;
    private String pictureUrl;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinTable(name="FOLLOWERS")
    private List<Userr> followers;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinTable(name="FOLLOWING")
    private List<Userr> following;

    @JsonIgnore
    @ManyToMany(mappedBy = "likes", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Tweet> likedTweets;

    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@JoinTable(name="OWNTWEETS")
    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade ={CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Tweet> ownTweets;


    @JsonCreator
    public Userr(@JsonProperty("id") Long id, @JsonProperty("name") String name,  @JsonProperty("userName") String userName,
    @JsonProperty("website") String website, @JsonProperty("pictureUrl") String pictureUrl, @JsonProperty("biografy") String biografy) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.website = website;
        this.biografy = biografy;
        this.pictureUrl = pictureUrl;
    }

    public Userr() {
    }

    public Userr(String password, String email, String name, String userName, String biografy, String locationX, String locationY, String website, String pictureUrl) {
        this.password = password;
        this.roles = new ArrayList<Role>();
        this.email = email;
        this.name = name;
        this.userName = userName;
        this.biografy = biografy;
        this.locationX = locationX;
        this.locationY = locationY;
        this.website = website;
        this.followers = new ArrayList<Userr>();
        this.following = new ArrayList<Userr>();
        this.likedTweets = new ArrayList<Tweet>();
        this.ownTweets = new ArrayList<Tweet>();
        this.pictureUrl = pictureUrl;
    }

    public Userr(Long id, List<Tweet> likedTweets) {
        this.userName = userName;
        this.likedTweets = likedTweets;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBiografy() {
        return biografy;
    }

    public void setBiografy(String biografy) {
        this.biografy = biografy;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Tweet> getLikedTweets() {
        return likedTweets;
    }

    public void setLikedTweets(List<Tweet> likedTweets) {
        this.likedTweets = likedTweets;
    }

    public List<Tweet> getOwnTweets() {
        return ownTweets;
    }

    public void setOwnTweets(List<Tweet> ownTweets) {
        this.ownTweets = ownTweets;
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public void addOwnTweet(Tweet tweet) {
        this.ownTweets.add(tweet);
    }

    public void addFollower(Userr user){
        this.followers.add(user);
    }

    public void addFollowing(Userr user){
        this.following.add(user);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id: ")
                .append(this.id).append("\n")
                .append("name: ")
                .append(this.name).append("\n")
                .append("userName: ")
                .append(this.userName).append("\n")
                .append("website: ")
                .append(this.website).append("\n")
                .append("biografy: ")
                .append(this.biografy).append("\n")
                .append("pictureUrl: ")
                .append(this.pictureUrl).append("\n");
        return stringBuilder.toString();
    }
    //    @Override
//    public int hashCode() {
//        HashCodeBuilder hcb = new HashCodeBuilder();
//        hcb.append(this.email);
//        return hcb.toHashCode();
//    }

    /*public List<Tweet> getOwnTweets() {
        return ownTweets;
    }
    
    public void setOwnTweets(List<Tweet> ownTweets) {
        this.ownTweets = ownTweets;
    }*/
    //*************************************************************************************************
    /*public Userr getFollower(String userName){
        return null;
    }

    public Userr getFollowedUser(String userName){
        return null;
    }*/

    /* nope
    public HashMap<Integer, Tweet> getTenMostRecentTweets(){
        int x;
        HashMap<Integer, Tweet> tempMap = new HashMap<Integer, Tweet>();

        // ophalen tweets uit database


        return this.ownTweets;
    }*/

    /*
    public List<Tweet> getMoreTweets(){

        // ophalen volgende 10 tweets uit het database

        return this.ownTweets;
    }

    public void addTweet(String content){
        // nieuwe tweet opbouwen

        // doorgeven aan het database

    }

    public boolean removeTweet(Tweet tweet){
        // tweet verwijderen uit het database


        // tweet verwijderen uit de lijst
        ownTweets.get(tweet.getId());
        return true;
    }*/
}
