package kwetter1.JPAtest;

import twitter_webservice.domain.Tweet;
import twitter_webservice.domain.Userr;

import java.sql.SQLException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;

@WebService
public class DatabaseCleaner {

    private final EntityManagerFactory emf;
    private EntityManager em;

    public DatabaseCleaner() {
        this.emf = Persistence.createEntityManagerFactory("DefaultPU");
    }

    private static final Class<?>[] ENTITY_TYPES = {
        Tweet.class,
        Userr.class,
    };

    @WebMethod(operationName = "clean")
    public void clean() {//throws SQLException { // EntityManager entityManager
        em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            for (Class<?> entityType : ENTITY_TYPES) {
                deleteEntities(entityType, em);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("JPQLValidation")
    private static void deleteEntities(Class<?> entityType, EntityManager entityManager) {
        entityManager.createQuery("delete from " + getEntityName(entityType, entityManager)).executeUpdate();
    }

    protected static String getEntityName(Class<?> clazz, EntityManager entityManager) {
        EntityType et = entityManager.getMetamodel().entity(clazz);
        return et.getName();
    }
}
