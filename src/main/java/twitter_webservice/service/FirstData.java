package twitter_webservice.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Anna-May on 12/04/2017.
 */
@Singleton
@Startup
public class FirstData {
    private EntityManager em;
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DefaultPU");

    private TestData testData;

    @PostConstruct
    public void init(){
//        testData = new TestData();
//        em = emf.createEntityManager();
//
//        testData.fillTestDataTweet();
//        testData.fillTestDataUserr();
//        em.persist(testData.getRole());
//        em.persist(testData.getUserOne());
//        em.persist(testData.getUserTwo());
//        em.persist(testData.getUserThree());
//        em.persist(testData.getUserFour());
    }
}
