package twitter_webservice.doa;

import twitter_webservice.domain.Userr;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna-May on 09/03/2017.
 */
@Stateless
@Default
public class UserrDAO_impl implements UsserDAO {

    @PersistenceContext(unitName = "DefaultPU")
    EntityManager em;

    public UserrDAO_impl() {
    }

    public Userr create(Userr user) {
        em.persist(user);
        return user;
    }

    public void edit(Userr user) {
        em.merge(user);
    }

    public List<Userr> findAll() {
        Query query = em.createNamedQuery("User.all", Userr.class);
        return (List<Userr>) query.getResultList();

//        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//        cq.select(cq.from(Userr.class));
//        return em.createQuery(cq).getResultList();
    }

    public Userr findByUserName(String userName) {
        Userr user = null;
        List<Userr> users = em.createNamedQuery("User.userByUserName").setParameter("userName", userName).getResultList();
        if(users.size() > 0){
            user = users.get(0);
        }
//        List<Tweet> tweets = user.getOwnTweets();
//        for (Tweet tweet : tweets) {
//            tweet.setOwner(null);
//        }

        return user;
    }

    public void remove(Userr user) {
        em.remove(em.merge(user));
    }

    public List<Userr> findFollowersByUserName(String userName) {
        List<Userr> users;
        Userr user;
        try{
            user = findByUserName(userName);
            users = user.getFollowers();
        } catch (Error er) {
            users = new ArrayList<Userr>();
        }
        return users;
    }

    public List<Userr> findFollowingByUserName(String userName) {
        List<Userr> users;
        Userr user;
        try{
            user = findByUserName(userName);
//            users = em.createNamedQuery("User.userByUserName").setParameter("userName", userName).getResultList();
//            Userr user;
//            user = users.get(0);
            users = user.getFollowing();
        } catch (Error er) {
            users = new ArrayList<Userr>();
        }
        return users;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}