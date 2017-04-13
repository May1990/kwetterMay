package kwetter1.JPAtest;

import org.junit.BeforeClass;
import org.junit.Test;
import twitter_webservice.doa.UserrDAO_impl;
import twitter_webservice.domain.Userr;
import twitter_webservice.service.TestData;
import twitter_webservice.service.UserMgr;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Anna-May on 16/03/2017.
 */
public class UserrMgrTest {

    private static UserMgr userMgr;
    private static UserrDAO_impl userDAO_impl;

    private static TestData testData;

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DefaultPUtest");
    private static EntityManager em;

    @BeforeClass
    public static void onceExecutedBeforeAll() {
        testData = new TestData();
        testData.fillTestDataTweet();
        testData.fillTestDataUserr();
        userDAO_impl = new UserrDAO_impl();
        userMgr = new UserMgr();
        userMgr.setUserDao_impl(userDAO_impl);

        em = emf.createEntityManager();
        userDAO_impl.setEntityManager(em);

        em.getTransaction().begin();
        em.persist(testData.getRole());
        em.getTransaction().commit();

        em.getTransaction().begin();
        userDAO_impl.create(testData.getUserOne());
        em.getTransaction().commit();

        em.getTransaction().begin();
        userDAO_impl.create(testData.getUserTwo());
        em.getTransaction().commit();

        em.getTransaction().begin();
        userDAO_impl.create(testData.getUserThree());
        em.getTransaction().commit();

        em.getTransaction().begin();
        userDAO_impl.create(testData.getUserFour());
        em.getTransaction().commit();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUsers(){
        List<Userr> listOfUsers = userMgr.getUsers();
        assertTrue(listOfUsers.size() >= 4);

        Userr userrTone = null;

        for(Userr userr: listOfUsers){
            if(userr.getId() == testData.getUserOne().getId()){
                userrTone = userr;
            }
        }

        assertEquals(testData.getUserOne().getBiografy(), userrTone.getBiografy());
        assertEquals(testData.getUserOne().getEmail(), userrTone.getEmail());
        assertEquals(testData.getUserOne().getLocationX(), userrTone.getLocationX());
        assertEquals(testData.getUserOne().getLocationY(), userrTone.getLocationY());
        assertEquals(testData.getUserOne().getName(), userrTone.getName());
        assertEquals(testData.getUserOne().getRoles().get(0).getRoleName(), userrTone.getRoles().get(0).getRoleName());
        assertEquals(testData.getUserOne().getUserName(), userrTone.getUserName());
        assertEquals(testData.getUserOne().getWebsite(), userrTone.getWebsite());
    }

    @Test
    public void getFollowers(){
        List<Userr> listOfUsers = userMgr.getFollowers(testData.getUserThree().getUserName());
        assertEquals(3, listOfUsers.size());

        Userr userTtwo = null;
        Userr expected = testData.getUserThree().getFollowers().get(1);
        for(Userr user: listOfUsers){
            if (user.getId() == expected.getId()){
                userTtwo = user;
            }
        }

        assertNotNull(userTtwo);

        assertNotNull(userTtwo.getId());
        assertEquals(expected.getBiografy(), userTtwo.getBiografy());
        assertEquals(expected.getEmail(), userTtwo.getEmail());
        assertEquals(expected.getLocationX(), userTtwo.getLocationX());
        assertEquals(expected.getLocationY(), userTtwo.getLocationY());
        assertEquals(expected.getName(), userTtwo.getName());

        assertEquals(expected.getRoles().get(0).getRoleName(), userTtwo.getRoles().get(0).getRoleName());
        assertEquals(expected.getUserName(), userTtwo.getUserName());
        assertEquals(expected.getWebsite(), userTtwo.getWebsite());
        assertEquals(expected.getFollowers(), userTtwo.getFollowers());
        assertEquals(expected.getFollowing(), userTtwo.getFollowing());
    }

    @Test
    public void getFollowing(){
        List<Userr> listOfUsers = userMgr.getFollowing(testData.getUserFour().getUserName());
        assertEquals(2, listOfUsers.size());

        Userr userTtwo = null;
        Userr expected = testData.getUserFour().getFollowers().get(0);
        for(Userr user: listOfUsers){
            if (user.getId() == expected.getId()){
                userTtwo = user;
            }
        }

        assertNotNull(userTtwo);

        assertEquals(expected.getBiografy(), userTtwo.getBiografy());
        assertEquals(expected.getEmail(), userTtwo.getEmail());
        assertEquals(expected.getLocationX(), userTtwo.getLocationX());
        assertEquals(expected.getLocationY(), userTtwo.getLocationY());
        assertEquals(expected.getName(), userTtwo.getName());

        assertEquals(expected.getRoles().get(0).getRoleName(), userTtwo.getRoles().get(0).getRoleName());
        assertEquals(expected.getUserName(), userTtwo.getUserName());
        assertEquals(expected.getWebsite(), userTtwo.getWebsite());
        assertEquals(expected.getFollowers(), userTtwo.getFollowers());
        assertEquals(expected.getFollowing(), userTtwo.getFollowing());
    }

    @Test
    public void getUserByUserName(){
        Userr actualUser = userMgr.getUserByUserName(testData.getUserOne().getUserName());
        assertNotNull(actualUser);

        assertEquals("user id:"+ testData.getUserOne().getId() + " username:" + testData.getUserOne().getUserName(),testData.getUserOne().getId(), actualUser.getId());
        assertEquals("user id:"+ testData.getUserOne().getId() + " username:" + testData.getUserOne().getUserName(),testData.getUserOne().getEmail(), actualUser.getEmail());
        assertEquals("user id:"+ testData.getUserOne().getId() + " username:" + testData.getUserOne().getUserName(),testData.getUserOne().getLocationX(), actualUser.getLocationX());
        assertEquals("user id:"+ testData.getUserOne().getId() + " username:" + testData.getUserOne().getUserName(),testData.getUserOne().getLocationY(), actualUser.getLocationY());
        assertEquals("user id:"+ testData.getUserOne().getId() + " username:" + testData.getUserOne().getUserName(),testData.getUserOne().getWebsite(), actualUser.getWebsite());
        assertEquals("user id:"+ testData.getUserOne().getId() + " username:" + testData.getUserOne().getUserName(),testData.getUserOne().getBiografy(), actualUser.getBiografy());
        assertEquals("user id:"+ testData.getUserOne().getId() + " username:" + testData.getUserOne().getUserName(),testData.getUserOne().getName(), actualUser.getName());
        assertEquals("user id:"+ testData.getUserOne().getId() + " username:" + testData.getUserOne().getUserName(),testData.getUserOne().getUserName(), actualUser.getUserName());
        assertEquals("user id:"+ testData.getUserOne().getId() + " username:" + testData.getUserOne().getUserName(),testData.getUserOne().getRoles().get(0), actualUser.getRoles().get(0));
    }

    @Test
    public void createUser(){
        // aanmaken gebruiker zonder followers, following, likedtweets en eigen tweets

        String password = generateSHApassword("test");
        String website = "http://www.sindala.com";
        String email = "fakiresindala@gmail.com";
        String name = "Fakire Sindala";
        String username = "lielielie1990";
        String biografie = "Hij speelde een hoofdrol in de Nederlandse televisieserie Floris (1969) van Paul Verhoeven.";
        String locationX = "5.8809216,17";
        String locationY = "51.0847923";

        em.getTransaction().begin();
        Userr userFive = userMgr.createUser(password,email,name,username,biografie,locationX,locationY,website);
        em.getTransaction().commit();

        assertNotNull(userFive.getId());
    }

    public String generateSHApassword(String password){
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
    }
}
