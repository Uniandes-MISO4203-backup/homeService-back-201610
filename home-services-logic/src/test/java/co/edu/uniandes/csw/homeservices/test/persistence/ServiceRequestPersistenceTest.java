package co.edu.uniandes.csw.homeservices.test.persistence;

import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import co.edu.uniandes.csw.homeservices.persistence.ServiceRequestPersistence;
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
public class ServiceRequestPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ServiceRequestEntity.class.getPackage())
                .addPackage(ServiceRequestPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private ServiceRequestPersistence serviceRequestPersistence;

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
        em.createQuery("delete from ServiceRequestEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<ServiceRequestEntity> data = new ArrayList<ServiceRequestEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ServiceRequestEntity entity = factory.manufacturePojo(ServiceRequestEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createServiceRequestTest() {
		PodamFactory factory = new PodamFactoryImpl();
        ServiceRequestEntity newEntity = factory.manufacturePojo(ServiceRequestEntity.class);
        ServiceRequestEntity result = serviceRequestPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ServiceRequestEntity entity = em.find(ServiceRequestEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getPrice(), entity.getPrice());
        Assert.assertEquals(newEntity.getRecommendedTime(), entity.getRecommendedTime());
        Assert.assertEquals(newEntity.getScore(), entity.getScore());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void getServiceRequestsTest() {
        List<ServiceRequestEntity> list = serviceRequestPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ServiceRequestEntity ent : list) {
            boolean found = false;
            for (ServiceRequestEntity entity : data) {
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
    public void getServiceRequestTest() {
        ServiceRequestEntity entity = data.get(0);
        ServiceRequestEntity newEntity = serviceRequestPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getPrice(), newEntity.getPrice());
        Assert.assertEquals(entity.getRecommendedTime(), newEntity.getRecommendedTime());
        Assert.assertEquals(entity.getCreationDate(), newEntity.getCreationDate());
        Assert.assertEquals(entity.getDueDate(), newEntity.getDueDate());
        Assert.assertEquals(entity.getScore(), newEntity.getScore());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void deleteServiceRequestTest() {
        ServiceRequestEntity entity = data.get(0);
        serviceRequestPersistence.delete(entity.getId());
        ServiceRequestEntity deleted = em.find(ServiceRequestEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateServiceRequestTest() {
        ServiceRequestEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ServiceRequestEntity newEntity = factory.manufacturePojo(ServiceRequestEntity.class);

        newEntity.setId(entity.getId());

        serviceRequestPersistence.update(newEntity);

        ServiceRequestEntity resp = em.find(ServiceRequestEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getPrice(), resp.getPrice());
        Assert.assertEquals(newEntity.getRecommendedTime(), resp.getRecommendedTime());
        Assert.assertEquals(newEntity.getScore(), resp.getScore());
        Assert.assertEquals(newEntity.getDescription(), resp.getDescription());
    }
}
