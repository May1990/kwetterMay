package kwetter1.JPAtest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import twitter_webservice.doa.TweetDAO_impl;
import twitter_webservice.doa.UserrDAO_impl;
import twitter_webservice.domain.Tweet;
import twitter_webservice.domain.Userr;
import twitter_webservice.service.TestData;
import twitter_webservice.service.TweetMgr;
import twitter_webservice.service.UserMgr;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by Anna-May on 16/03/2017.
 */
public class TweetMgrTest {

    private UserMgr userMgr;
    private TweetMgr tweetMgr;

    private static UserrDAO_impl userDAO_impl;
    private TweetDAO_impl tweetDAO_impl;

    private static TestData testData;

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DefaultPUtest");
    private static EntityManager em;

    @BeforeClass
    public static void onceExecutedBeforeAll() {
        testData = new TestData();
        testData.fillTestDataTweet();
        testData.fillTestDataUserr();
        userDAO_impl = new UserrDAO_impl();
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


    @Before
    public void setUp() throws Exception {
        tweetDAO_impl = new TweetDAO_impl();
        userMgr = new UserMgr();
        tweetMgr = new TweetMgr();
        userMgr.setUserDao_impl(userDAO_impl);
        tweetMgr.setTweetDao_impl(tweetDAO_impl);
        tweetMgr.setUserDao_impl(userDAO_impl);
        tweetDAO_impl.setEntityManager(em);
    }

    @Test
    public void createTweetTest(){
        String content = "Van Afrika tot in Amerika, het is zoveel mooier als we samen zijn.";

        em.getTransaction().begin();
        Tweet tweet = null;
        //Tweet tweet = tweetMgr.createTweet(content, testData.getUserThree().getUserName());
        em.getTransaction().commit();

        assertNotNull(tweet.getId());
        assertEquals(content, tweet.getContent());

        List<Tweet> tweets = tweetMgr.getTweetsByUserName(testData.getUserThree().getUserName());
        Tweet result = null;
        for(Tweet tweett: tweets){
            if(tweett.getId() == tweet.getId()){
                result = tweett;
            }
        }

        assertEquals(content, result.getContent());
        assertEquals(tweet.getContent(), result.getContent());
        assertEquals(tweet.getDate(), result.getDate());
        assertEquals(tweet.getOwner().getId(), result.getOwner().getId());
    }

    @Test
    public void getTweetsTest(){
        List<Tweet> tweets = tweetDAO_impl.findAll();

        Tweet actual = null;
        int count = 0;
        for (Tweet tweet : tweets) {
            if(tweet.getId() == testData.getTweetSix().getId() || tweet.getId() == testData.getTweetSeven().getId()|| tweet.getId() == testData.getTweetEight().getId()){
                count++;
            }
            if(tweet.getId() == testData.getTweetSix().getId()){
                actual = tweet;
            }
        }

        assertEquals(3, count);

        Tweet expected = testData.getTweetSix();

        assertEquals("tweet id:"+ expected.getId() + " content:" + expected.getContent(),expected.getContent(), actual.getContent());
        assertEquals("tweet id:"+ expected.getId() + " content:" + expected.getContent(),expected.getDate(), actual.getDate());

        assertEquals("tweet id:"+ expected.getId() + " content:" + expected.getContent(),expected.getOwner().getId(), actual.getOwner().getId());
        assertEquals("tweet id:"+ expected.getId() + " content:" + expected.getContent(),expected.getOwner().getEmail(), actual.getOwner().getEmail());
        assertEquals("tweet id:"+ expected.getId() + " content:" + expected.getContent(),expected.getOwner().getLocationX(), actual.getOwner().getLocationX());
        assertEquals("tweet id:"+ expected.getId() + " content:" + expected.getContent(),expected.getOwner().getLocationY(), actual.getOwner().getLocationY());
        assertEquals("tweet id:"+ expected.getId() + " content:" + expected.getContent(),expected.getOwner().getWebsite(), actual.getOwner().getWebsite());
        assertEquals("tweet id:"+ expected.getId() + " content:" + expected.getContent(),expected.getOwner().getBiografy(), actual.getOwner().getBiografy());
        assertEquals("tweet id:"+ expected.getId() + " content:" + expected.getContent(),expected.getOwner().getName(), actual.getOwner().getName());
        assertEquals("tweet id:"+ expected.getId() + " content:" + expected.getContent(),expected.getOwner().getUserName(), actual.getOwner().getUserName());

        List<Userr> testUsers = actual.getLikes();
        assertEquals(expected.getLikes().size(), testUsers.size());

        Userr actualUser = testUsers.get(1);
        Userr expectedUser = expected.getLikes().get(1);
        assertEquals(testData.getTweetSix().getLikes().get(1).getId(),testUsers.get(1).getId());
        assertEquals("user id:"+ expectedUser.getId() + " username:" + expectedUser.getUserName(),expectedUser.getId(), actualUser.getId());
        assertEquals("user id:"+ expectedUser.getId() + " username:" + expectedUser.getUserName(),expectedUser.getEmail(), actualUser.getEmail());
        assertEquals("user id:"+ expectedUser.getId() + " username:" + expectedUser.getUserName(),expectedUser.getLocationX(), actualUser.getLocationX());
        assertEquals("user id:"+ expectedUser.getId() + " username:" + expectedUser.getUserName(),expectedUser.getLocationY(), actualUser.getLocationY());
        assertEquals("user id:"+ expectedUser.getId() + " username:" + expectedUser.getUserName(),expectedUser.getWebsite(), actualUser.getWebsite());
        assertEquals("user id:"+ expectedUser.getId() + " username:" + expectedUser.getUserName(),expectedUser.getBiografy(), actualUser.getBiografy());
        assertEquals("user id:"+ expectedUser.getId() + " username:" + expectedUser.getUserName(),expectedUser.getName(), actualUser.getName());
        assertEquals("user id:"+ expectedUser.getId() + " username:" + expectedUser.getUserName(),expectedUser.getUserName(), actualUser.getUserName());
    }

    @Test
    public void getTweetsByUserTest() {
        List<Tweet> tweetsTOne = tweetMgr.getTweetsByUserName(testData.getUserFour().getUserName());

        assertEquals(3, tweetsTOne.size());
        Tweet tweetTOne = null;
        for(Tweet tweet: tweetsTOne){
            if(tweet.getId() == testData.getTweetFour().getId()){
                tweetTOne = tweet;
            }
        }
        assertNotNull(tweetTOne);

        assertEquals("tweet id:"+ testData.getTweetFour().getId() + " content:" + testData.getTweetFour().getContent(),testData.getTweetFour().getContent(), tweetTOne.getContent());
        assertEquals("tweet id:"+ testData.getTweetFour().getId() + " content:" + testData.getTweetFour().getContent(),testData.getTweetFour().getDate(), tweetTOne.getDate());

        assertEquals("tweet id:"+ testData.getTweetFour().getId() + " content:" + testData.getTweetFour().getContent(),testData.getTweetFour().getOwner().getId(), tweetTOne.getOwner().getId());
    }
}
