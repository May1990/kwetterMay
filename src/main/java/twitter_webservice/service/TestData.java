package twitter_webservice.service;

import twitter_webservice.domain.Role;
import twitter_webservice.domain.Tweet;
import twitter_webservice.domain.Userr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Anna-May on 05/04/2017.
 */
public class TestData {
    private Role role;

    private Userr userOne;
    private Userr userTwo;
    private Userr userThree;
    private Userr userFour;

    private Tweet tweetOne;
    private Tweet tweetTwo;
    private Tweet tweetThree;
    private Tweet tweetFour;
    private Tweet tweetFive;
    private Tweet tweetSix;
    private Tweet tweetSeven;
    private Tweet tweetEight;

    public TestData() {
    }

    public Userr getUserOne() {
        return userOne;
    }

    public Userr getUserTwo() {
        return userTwo;
    }

    public Userr getUserThree() {
        return userThree;
    }

    public Userr getUserFour() {
        return userFour;
    }

    public Tweet getTweetOne() {
        return tweetOne;
    }

    public Tweet getTweetTwo() {
        return tweetTwo;
    }

    public Tweet getTweetThree() {
        return tweetThree;
    }

    public Tweet getTweetFour() {
        return tweetFour;
    }

    public Tweet getTweetFive() {
        return tweetFive;
    }

    public Tweet getTweetSix() {
        return tweetSix;
    }

    public Tweet getTweetSeven() {
        return tweetSeven;
    }

    public Tweet getTweetEight() {
        return tweetEight;
    }

    public Role getRole() {
        return role;
    }

    public String generateSHApassword(String password){
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
    }

    public void fillTestDataUserr(){
        userOne.addFollowing(userThree);
        userOne.addFollower(userThree);

        userTwo.addFollowing(userThree);
        userTwo.addFollowing(userFour);
        userTwo.addFollower(userThree);
        userTwo.addFollower(userFour);

        userThree.addFollowing(userOne);
        userThree.addFollowing(userTwo);
        userThree.addFollower(userOne);
        userThree.addFollower(userTwo);
        userThree.addFollower(userFour);

        userFour.addFollowing(userTwo);
        userFour.addFollowing(userThree);
        userFour.addFollower(userTwo);

        tweetOne.addLike(userFour);
        tweetOne.addLike(userTwo);

        tweetTwo.addLike(userTwo);
        tweetTwo.addLike(userOne);

        tweetFour.addLike(userTwo);

        tweetFive.addLike(userTwo);
        tweetFive.addLike(userFour);

        tweetSix.addLike(userTwo);
        tweetSix.addLike(userOne);
        tweetSix.addLike(userThree);

        tweetEight.addLike(userFour);
        tweetEight.addLike(userOne);
        tweetEight.addLike(userThree);
    }

    public void fillTestDataTweet(){
        tweetOne = new Tweet(
                new Date(),
                "Je moet niet zitten aan mijn nikes!",
                null
        );

        tweetTwo = new Tweet(
                new Date(),
                "I know I'm running my mouth, I will nog be accounted for what comes out uh.",
                null
        );

        tweetThree = new Tweet(
                new Date(),
                "Je leert niet voor school, maar voor het leven.",
                null
        );

        tweetFour = new Tweet(
                new Date(),
                "Trust in the timing of your life.",
                null
        );

        tweetFive = new Tweet(
                new Date(),
                "Kaas, kaas, pasta! Linker pan!",
                null
        );

        tweetSix = new Tweet(
                new Date(),
                "Eh klein meisje, oh, excuseer, kleine jongen. Je weet dat er in het leven niet alleen goed of slecht is.",
                null
        );

        tweetSeven = new Tweet(
                new Date(),
                "Quickly moving towards a storm. Moving forward, torn into pieces over reasons of what these storms are for.",
                null
        );

        tweetEight = new Tweet(
                new Date(),
                "Lieverd, lieverd. Waarom stress je mij met zinnen? Ik ben op straat, ik ben met dieven, met dieven.",
                null
        );

        userOne = new Userr(
                generateSHApassword("test"),
                "josbergman@gmail.com",
                "Jos Bergman",
                "jos1940",
                "Hij speelde een hoofdrol in de Nederlandse televisieserie Floris (1969) van Paul Verhoeven.",
                "5.8809216,17",
                "51.0847923",
                "http://www.josbergman.com"
        );

        userTwo = new Userr(
                generateSHApassword("test"),
                "florisvanrosemondt@gmail.com",
                "Floris van Rosemondt",
                "floris1944",
                "Rutger Oelsen Hauer (Breukelen, 23 januari 1944) is een Nederlands acteur ",
                "5.8809216,17",
                "51.0847923",
                "http://www.florisvanrosemondt.com"
        );
        userTwo.addOwnTweet(tweetSix);
        userTwo.addOwnTweet(tweetSeven);
        userTwo.addOwnTweet(tweetEight);

        userThree = new Userr(
                generateSHApassword("test"),
                "rutgerhauer@gmail.com",
                "Rutger Hauer",
                "Rutger1944",
                "Rutger Oelsen Hauer (Breukelen, 23 januari 1944) is een Nederlands acteur ",
                "5.8809216,17",
                "51.0847923",
                "http://www.rutgerhauer.com"
        );
        userThree.addOwnTweet(tweetOne);
        userThree.addOwnTweet(tweetTwo);

        userFour = new Userr(
                generateSHApassword("test"),
                "fakiresindala@gmail.com",
                "Fakire Sindala",
                "sindala1940",
                "Hij speelde een hoofdrol in de Nederlandse televisieserie Floris (1969) van Paul Verhoeven.",
                "5.8809216,17",
                "51.0847923",
                "http://www.sindala.com"
        );
        userFour.addOwnTweet(tweetThree);
        userFour.addOwnTweet(tweetFour);
        userFour.addOwnTweet(tweetFive);

        role = new Role("regulars");
        role.addUser(userOne);
        role.addUser(userTwo);
        role.addUser(userThree);
        role.addUser(userFour);

        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        userOne.setRoles(roles);

        roles = new ArrayList<Role>();
        roles.add(role);
        userTwo.setRoles(roles);

        roles = new ArrayList<Role>();
        roles.add(role);
        userThree.setRoles(roles);

        roles = new ArrayList<Role>();
        roles.add(role);
        userFour.setRoles(roles);

        tweetOne.setOwner(userThree);
        tweetTwo.setOwner(userThree);

        tweetThree.setOwner(userFour);
        tweetFour.setOwner(userFour);
        tweetFive.setOwner(userFour);

        tweetSix.setOwner(userTwo);
        tweetSeven.setOwner(userTwo);
        tweetEight.setOwner(userTwo);
    }
}
