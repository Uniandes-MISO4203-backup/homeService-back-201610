package co.edu.uniandes.csw.homeservices.test.logic;

import co.edu.uniandes.csw.homeservices.ejbs.CustomerLogic;
import co.edu.uniandes.csw.homeservices.api.ICustomerLogic;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import co.edu.uniandes.csw.homeservices.persistence.CustomerPersistence;
import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
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
public class CustomerLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ICustomerLogic customerLogic;

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
    private List<CustomerEntity> data = new ArrayList<CustomerEntity>();

    /**
     * @generated
     */
    private List<ServiceRequestEntity> serviceRequestsData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CustomerEntity.class.getPackage())
                .addPackage(CustomerLogic.class.getPackage())
                .addPackage(ICustomerLogic.class.getPackage())
                .addPackage(CustomerPersistence.class.getPackage())
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
        em.createQuery("delete from ServiceRequestEntity").executeUpdate();
        em.createQuery("delete from CustomerEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ServiceRequestEntity serviceRequests = factory.manufacturePojo(ServiceRequestEntity.class);
            em.persist(serviceRequests);
            serviceRequestsData.add(serviceRequests);
        }

        for (int i = 0; i < 3; i++) {
            CustomerEntity entity = factory.manufacturePojo(CustomerEntity.class);

            em.persist(entity);
            data.add(entity);

            if (i == 0) {
                serviceRequestsData.get(i).setCustomer(entity);
            }
        }
    }

    /**
     * @generated
     */
    @Test
    public void createCustomerTest() {
        CustomerEntity entity = factory.manufacturePojo(CustomerEntity.class);
        CustomerEntity result = customerLogic.createCustomer(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getLastName(), entity.getLastName());
        Assert.assertEquals(result.getDocument(), entity.getDocument());
    }

    /**
     * @generated
     */
    @Test
    public void getCustomersTest() {
        List<CustomerEntity> list = customerLogic.getCustomers();
        Assert.assertEquals(data.size(), list.size());
        for (CustomerEntity entity : list) {
            boolean found = false;
            for (CustomerEntity storedEntity : data) {
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
    public void getCustomerTest() {
        CustomerEntity entity = data.get(0);
        CustomerEntity resultEntity = customerLogic.getCustomer(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getLastName(), resultEntity.getLastName());
        Assert.assertEquals(entity.getDocument(), resultEntity.getDocument());
    }

    /**
     * @generated
     */
    @Test
    public void deleteCustomerTest() {
        CustomerEntity entity = data.get(1);
        customerLogic.deleteCustomer(entity.getId());
        CustomerEntity deleted = em.find(CustomerEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateCustomerTest() {
        CustomerEntity entity = data.get(0);
        CustomerEntity pojoEntity = factory.manufacturePojo(CustomerEntity.class);

        pojoEntity.setId(entity.getId());

        customerLogic.updateCustomer(pojoEntity);

        CustomerEntity resp = em.find(CustomerEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getLastName(), resp.getLastName());
        Assert.assertEquals(pojoEntity.getDocument(), resp.getDocument());
    }

    /**
     * @generated
     */
    @Test
    public void getServiceRequestsTest() {
        CustomerEntity entity = data.get(0);
        ServiceRequestEntity serviceRequestEntity = serviceRequestsData.get(0);
        ServiceRequestEntity response = customerLogic.getServiceRequests(entity.getId(), serviceRequestEntity.getId());

        Assert.assertEquals(serviceRequestEntity.getId(), response.getId());
        Assert.assertEquals(serviceRequestEntity.getName(), response.getName());
        Assert.assertEquals(serviceRequestEntity.getPrice(), response.getPrice());
        Assert.assertEquals(serviceRequestEntity.getRecommendedTime(), response.getRecommendedTime());
        Assert.assertEquals(serviceRequestEntity.getCreationDate(), response.getCreationDate());
        Assert.assertEquals(serviceRequestEntity.getDueDate(), response.getDueDate());
        Assert.assertEquals(serviceRequestEntity.getStatusService(), response.getStatusService());
    }

    /**
     * @generated
     */
    @Test
    public void listServiceRequestsTest() {
        List<ServiceRequestEntity> list = customerLogic.listServiceRequests(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addServiceRequestsTest() {
        CustomerEntity entity = data.get(0);
        ServiceRequestEntity serviceRequestEntity = serviceRequestsData.get(1);
        ServiceRequestEntity response = customerLogic.addServiceRequests(entity.getId(), serviceRequestEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(serviceRequestEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceServiceRequestsTest() {
        CustomerEntity entity = data.get(0);
        List<ServiceRequestEntity> list = serviceRequestsData.subList(1, 3);
        customerLogic.replaceServiceRequests(entity.getId(), list);

        entity = customerLogic.getCustomer(entity.getId());
        Assert.assertFalse(entity.getServiceRequests().contains(serviceRequestsData.get(0)));
        Assert.assertTrue(entity.getServiceRequests().contains(serviceRequestsData.get(1)));
        Assert.assertTrue(entity.getServiceRequests().contains(serviceRequestsData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeServiceRequestsTest() {
        customerLogic.removeServiceRequests(data.get(0).getId(), serviceRequestsData.get(0).getId());
        ServiceRequestEntity response = customerLogic.getServiceRequests(data.get(0).getId(), serviceRequestsData.get(0).getId());
        Assert.assertNull(response);
    }
}
