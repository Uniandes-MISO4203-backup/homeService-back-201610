package co.edu.uniandes.csw.homeservices.test.logic;

import co.edu.uniandes.csw.homeservices.ejbs.ReviewLogic;
import co.edu.uniandes.csw.homeservices.api.IReviewLogic;
import co.edu.uniandes.csw.homeservices.entities.ReviewEntity;
import co.edu.uniandes.csw.homeservices.persistence.ReviewPersistence;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
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
public class ReviewLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IReviewLogic reviewLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<ReviewEntity> data = new ArrayList<ReviewEntity>();

    /**
     * @generated
     */
    private List<ContractorEntity> contractorData = new ArrayList<>();

    /**
     * @generated
     */
    private List<CustomerEntity> customerData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReviewEntity.class.getPackage())
                .addPackage(ReviewLogic.class.getPackage())
                .addPackage(IReviewLogic.class.getPackage())
                .addPackage(ReviewPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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
        em.createQuery("delete from ContractorEntity").executeUpdate();
        em.createQuery("delete from CustomerEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ContractorEntity contractor = factory.manufacturePojo(ContractorEntity.class);
            em.persist(contractor);
            contractorData.add(contractor);
        }

        for (int i = 0; i < 3; i++) {
            CustomerEntity customer = factory.manufacturePojo(CustomerEntity.class);
            em.persist(customer);
            customerData.add(customer);
        }

        for (int i = 0; i < 3; i++) {
            ReviewEntity entity = factory.manufacturePojo(ReviewEntity.class);

            entity.setContractor(contractorData.get(0));

            entity.setCustomer(customerData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createReviewTest() {
        ReviewEntity entity = factory.manufacturePojo(ReviewEntity.class);
        ReviewEntity result = reviewLogic.createReview(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getValue(), entity.getValue());
    }

    /**
     * @generated
     */
    @Test
    public void getReviewsTest() {
        List<ReviewEntity> list = reviewLogic.getReviews();
        Assert.assertEquals(data.size(), list.size());
        for (ReviewEntity entity : list) {
            boolean found = false;
            for (ReviewEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
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
    public void getReviewTest() {
        ReviewEntity entity = data.get(0);
        ReviewEntity resultEntity = reviewLogic.getReview(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getValue(), resultEntity.getValue());
    }

    /**
     * @generated
     */
    @Test
    public void deleteReviewTest() {
        ReviewEntity entity = data.get(1);
        reviewLogic.deleteReview(entity.getId());
        ReviewEntity deleted = em.find(ReviewEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateReviewTest() {
        ReviewEntity entity = data.get(0);
        ReviewEntity pojoEntity = factory.manufacturePojo(ReviewEntity.class);

        pojoEntity.setId(entity.getId());

        reviewLogic.updateReview(pojoEntity);

        ReviewEntity resp = em.find(ReviewEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getValue(), resp.getValue());
    }
}
