package twitter_webservice.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Anna-May on 09/03/2017.
 */
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        //scope = Userr.class,               resolver = JPAObjectIdResolver.class,
//        property = "id")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQueries({
        @NamedQuery(name = "Tweet.all", query = "SELECT c FROM Tweet c"),
        @NamedQuery(name = "Tweet.tweetsByUser", query = "SELECT c FROM Tweet c WHERE c.owner = :user"),
        @NamedQuery(name = "Tweet.tweetsCountByUser", query = "SELECT count(c.id) FROM Tweet c WHERE c.owner = :user"),
        @NamedQuery(name = "Tweet.tweetsById", query = "SELECT c FROM Tweet c WHERE c.owner.id = :userId")
})
public class Tweet implements Serializable, Comparable<Tweet> {
    @JsonCreator
    public Tweet(@JsonProperty("id") Long id, @JsonProperty("content") String content, @JsonProperty("owner") Userr owner){
        this.id = id;
        this.content = content;
        this.owner = owner;
        this.timeAgo = refreshTimeAgo();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    private String content;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="LIKEDTWEETS")
    private List<Userr> likes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "owner_id", referencedColumnName = "ID")
    private Userr owner;

    @Transient
    private String timeAgo;

    public Tweet() {

    }

    public Tweet(Date date, String content,Userr owner) {
        this.date = date;
        this.content = content;
        this.likes = new ArrayList<Userr>();
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public List<Userr> getLikes() {
        return likes;
    }

    public void setLikes(List<Userr> likes) {
        this.likes = likes;
    }

    public Userr getOwner() {
        return owner;
    }

    public void setOwner(Userr owner) {
        this.owner = owner;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public void addLike(Userr user){
        likes.add(user);
    }

    public String refreshTimeAgo(){
        long diff = new Date().getTime() - date.getTime();
        long seconds = diff / 1000 % 60;
        long minutes = diff / (60 * 1000) % 60;

        timeAgo = minutes + " minuten en " + seconds + " seconden geleden";
        return timeAgo;
    }

    public int compareTo(Tweet o) {
        return getDate().compareTo(o.getDate());
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id: ")
                .append(this.id).append("\n")
                .append("content: ")
                .append(this.content).append("\n")
                .append("owner: ")
                .append(this.owner).append("\n");
        return stringBuilder.toString();
    }

    //    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }

    //**********************************************************************************************************
    /*public boolean addLike(Userr user){
        if(user instanceof Userr && !likes.contains(user)){
            // ook database he :P
            likes.add(user);
            return true;
        }else{
            return false;
        }
    }

    public boolean removeLike(Userr user){
        if(user instanceof Userr && likes.contains(user)){
            //verwijderen met id, referencie niet het zelfde
            likes.remove(user);
            return true;
        }else{
            return false;
        }
    }*/
}
