package co.edu.uniandes.csw.homeservices.test.persistence;

import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import co.edu.uniandes.csw.homeservices.persistence.CustomerPersistence;
import java.util.ArrayList;
import java.util.List;

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
public class CustomerPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CustomerEntity.class.getPackage())
                .addPackage(CustomerPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private CustomerPersistence customerPersistence;

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
        em.createQuery("delete from CustomerEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<CustomerEntity> data = new ArrayList<CustomerEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CustomerEntity entity = factory.manufacturePojo(CustomerEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createCustomerTest() {
		PodamFactory factory = new PodamFactoryImpl();
        CustomerEntity newEntity = factory.manufacturePojo(CustomerEntity.class);
        CustomerEntity result = customerPersistence.create(newEntity);

        Assert.assertNotNull(result);

        CustomerEntity entity = em.find(CustomerEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getLastName(), entity.getLastName());
        Assert.assertEquals(newEntity.getDocument(), entity.getDocument());
        Assert.assertEquals(newEntity.getPicture(), entity.getPicture());
    }

    /**
     * @generated
     */
    @Test
    public void getCustomersTest() {
        List<CustomerEntity> list = customerPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CustomerEntity ent : list) {
            boolean found = false;
            for (CustomerEntity entity : data) {
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
    public void getCustomerTest() {
        CustomerEntity entity = data.get(0);
        CustomerEntity newEntity = customerPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getLastName(), newEntity.getLastName());
        Assert.assertEquals(entity.getDocument(), newEntity.getDocument());
        Assert.assertEquals(entity.getPicture(), newEntity.getPicture());
    }

    /**
     * @generated
     */
    @Test
    public void deleteCustomerTest() {
        CustomerEntity entity = data.get(0);
        customerPersistence.delete(entity.getId());
        CustomerEntity deleted = em.find(CustomerEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateCustomerTest() {
        CustomerEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CustomerEntity newEntity = factory.manufacturePojo(CustomerEntity.class);

        newEntity.setId(entity.getId());

        customerPersistence.update(newEntity);

        CustomerEntity resp = em.find(CustomerEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getLastName(), resp.getLastName());
        Assert.assertEquals(newEntity.getDocument(), resp.getDocument());
        Assert.assertEquals(newEntity.getPicture(), resp.getPicture());
    }
}
