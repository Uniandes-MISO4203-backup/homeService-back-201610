package co.edu.uniandes.csw.homeservices.test.persistence;

import co.edu.uniandes.csw.homeservices.entities.ReviewEntity;
import co.edu.uniandes.csw.homeservices.persistence.ReviewPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class ReviewPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReviewEntity.class.getPackage())
                .addPackage(ReviewPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private ReviewPersistence correctionRequestPersistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from ReviewEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<ReviewEntity> data = new ArrayList<ReviewEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ReviewEntity entity = factory.manufacturePojo(ReviewEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createReviewTest() {
		PodamFactory factory = new PodamFactoryImpl();
        ReviewEntity newEntity = factory.manufacturePojo(ReviewEntity.class);
        ReviewEntity result = correctionRequestPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ReviewEntity entity = em.find(ReviewEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getReviewTest() {
        List<ReviewEntity> list = correctionRequestPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ReviewEntity ent : list) {
            boolean found = false;
            for (ReviewEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * @generated
     */
    @Test
    public void getReviewsTest() {
        ReviewEntity entity = data.get(0);
        ReviewEntity newEntity = correctionRequestPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
        Assert.assertEquals(entity.getValue(), newEntity.getValue());
    }

    /**
     * @generated
     */
    @Test
    public void deleteReviewTest() {
        ReviewEntity entity = data.get(0);
        correctionRequestPersistence.delete(entity.getId());
        ReviewEntity deleted = em.find(ReviewEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateReviewTest() {
        ReviewEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReviewEntity newEntity = factory.manufacturePojo(ReviewEntity.class);

        newEntity.setId(entity.getId());

        correctionRequestPersistence.update(newEntity);

        ReviewEntity resp = em.find(ReviewEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
