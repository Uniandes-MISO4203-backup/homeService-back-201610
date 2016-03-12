package co.edu.uniandes.csw.homeservices.test.logic;

import co.edu.uniandes.csw.homeservices.ejbs.ServiceRequestLogic;
import co.edu.uniandes.csw.homeservices.api.IServiceRequestLogic;
import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import co.edu.uniandes.csw.homeservices.persistence.ServiceRequestPersistence;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import co.edu.uniandes.csw.homeservices.entities.CategoryEntity;
import co.edu.uniandes.csw.homeservices.entities.StatusEntity;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
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
public class ServiceRequestLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IServiceRequestLogic serviceRequestLogic;

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
    private List<ServiceRequestEntity> data = new ArrayList<ServiceRequestEntity>();

    /**
     * @generated
     */
    private List<SkillEntity> expectedskillsData = new ArrayList<>();

    /**
     * @generated
     */
    private List<CategoryEntity> categoryData = new ArrayList<>();

    /**
     * @generated
     */
    private List<CustomerEntity> customerData = new ArrayList<>();

    /**
     * @generated
     */
    private List<StatusEntity> statusData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ServiceRequestEntity.class.getPackage())
                .addPackage(ServiceRequestLogic.class.getPackage())
                .addPackage(IServiceRequestLogic.class.getPackage())
                .addPackage(ServiceRequestPersistence.class.getPackage())
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
        em.createQuery("delete from SkillEntity").executeUpdate();
        em.createQuery("delete from CategoryEntity").executeUpdate();
        em.createQuery("delete from CustomerEntity").executeUpdate();
        em.createQuery("delete from StatusEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SkillEntity expectedskills = factory.manufacturePojo(SkillEntity.class);
            em.persist(expectedskills);
            expectedskillsData.add(expectedskills);
        }

        for (int i = 0; i < 3; i++) {
            CategoryEntity category = factory.manufacturePojo(CategoryEntity.class);
            em.persist(category);
            categoryData.add(category);
        }

        for (int i = 0; i < 3; i++) {
            CustomerEntity customer = factory.manufacturePojo(CustomerEntity.class);
            em.persist(customer);
            customerData.add(customer);
        }
        
        
        for (int i = 0; i < 3; i++) {
            /*For create statusEntity with especified ids (1,2,3)*/
            String query = "INSERT INTO StatusEntity  (id, name) " +
            "VALUES  ("+(i+1)+",'Status"+(i+1)+"')";
            em.createNativeQuery(query).executeUpdate();
            StatusEntity status= em.find(StatusEntity.class, new Long(i+1));
            statusData.add(status);
        }

        for (int i = 0; i < 3; i++) {
            ServiceRequestEntity entity = factory.manufacturePojo(ServiceRequestEntity.class);

            entity.getExpectedskills().add(expectedskillsData.get(0));

            entity.setCategory(categoryData.get(0));

            entity.setCustomer(customerData.get(0));

            entity.setStatus(statusData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createServiceRequestTest() {
        ServiceRequestEntity entity = factory.manufacturePojo(ServiceRequestEntity.class);
        ServiceRequestEntity result = serviceRequestLogic.createServiceRequest(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getPrice(), entity.getPrice());
        Assert.assertEquals(result.getRecommendedTime(), entity.getRecommendedTime());
        Assert.assertEquals(result.getCreationDate(), entity.getCreationDate());
        Assert.assertEquals(result.getDueDate(), entity.getDueDate());
    }

    /**
     * @generated
     */
    @Test
    public void getServiceRequestsTest() {
        List<ServiceRequestEntity> list = serviceRequestLogic.getServiceRequests();
        Assert.assertEquals(data.size(), list.size());
        for (ServiceRequestEntity entity : list) {
            boolean found = false;
            for (ServiceRequestEntity storedEntity : data) {
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
    public void getServiceRequestTest() {
        ServiceRequestEntity entity = data.get(0);
        ServiceRequestEntity resultEntity = serviceRequestLogic.getServiceRequest(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getPrice(), resultEntity.getPrice());
        Assert.assertEquals(entity.getRecommendedTime(), resultEntity.getRecommendedTime());
        Assert.assertEquals(entity.getCreationDate(), resultEntity.getCreationDate());
        Assert.assertEquals(entity.getDueDate(), resultEntity.getDueDate());
    }

    /**
     * @generated
     */
    @Test
    public void deleteServiceRequestTest() {
        ServiceRequestEntity entity = data.get(1);
        serviceRequestLogic.deleteServiceRequest(entity.getId());
        ServiceRequestEntity deleted = em.find(ServiceRequestEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateServiceRequestTest() {
        ServiceRequestEntity entity = data.get(0);
        ServiceRequestEntity pojoEntity = factory.manufacturePojo(ServiceRequestEntity.class);

        pojoEntity.setId(entity.getId());
        StatusEntity status= em.find(StatusEntity.class, StatusEntity.FINISHED);
        pojoEntity.setStatus(status);
        pojoEntity.setScore(5);
        
        serviceRequestLogic.updateServiceRequest(pojoEntity);

        ServiceRequestEntity resp = em.find(ServiceRequestEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getPrice(), resp.getPrice());
        Assert.assertEquals(pojoEntity.getRecommendedTime(), resp.getRecommendedTime());
        Assert.assertEquals(pojoEntity.getCreationDate(), resp.getCreationDate());
        Assert.assertEquals(pojoEntity.getDueDate(), resp.getDueDate());
        Assert.assertEquals(pojoEntity.getStatus().getId(), resp.getStatus().getId());
        Assert.assertEquals(pojoEntity.getScore(),resp.getScore());
    }
    /**
     * @generated
     */
    @Test
    public void updateServiceRequestTest2() {
        ServiceRequestEntity entity = data.get(0);
        ServiceRequestEntity pojoEntity = factory.manufacturePojo(ServiceRequestEntity.class);

        pojoEntity.setId(entity.getId());
        StatusEntity status= statusData.get(0);
        pojoEntity.setStatus(status);
        pojoEntity.setScore(5);
        serviceRequestLogic.updateServiceRequest(pojoEntity);

        ServiceRequestEntity resp = em.find(ServiceRequestEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getPrice(), resp.getPrice());
        Assert.assertEquals(pojoEntity.getRecommendedTime(), resp.getRecommendedTime());
        Assert.assertEquals(pojoEntity.getCreationDate(), resp.getCreationDate());
        Assert.assertEquals(pojoEntity.getDueDate(), resp.getDueDate());
        Assert.assertEquals(pojoEntity.getStatus().getId(), resp.getStatus().getId());
        Assert.assertNull(resp.getScore());
    }

    /**
     * @generated
     */
    @Test
    public void getExpectedskillsTest() {
        ServiceRequestEntity entity = data.get(0);
        SkillEntity skillEntity = expectedskillsData.get(0);
        SkillEntity response = serviceRequestLogic.getExpectedskills(entity.getId(), skillEntity.getId());

        Assert.assertEquals(skillEntity.getId(), response.getId());
        Assert.assertEquals(skillEntity.getName(), response.getName());
        Assert.assertEquals(skillEntity.getDescription(), response.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void listExpectedskillsTest() {
        List<SkillEntity> list = serviceRequestLogic.listExpectedskills(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addExpectedskillsTest() {
        ServiceRequestEntity entity = data.get(0);
        SkillEntity skillEntity = expectedskillsData.get(1);
        SkillEntity response = serviceRequestLogic.addExpectedskills(entity.getId(), skillEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(skillEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceExpectedskillsTest() {
        ServiceRequestEntity entity = data.get(0);
        List<SkillEntity> list = expectedskillsData.subList(1, 3);
        serviceRequestLogic.replaceExpectedskills(entity.getId(), list);

        entity = serviceRequestLogic.getServiceRequest(entity.getId());
        Assert.assertFalse(entity.getExpectedskills().contains(expectedskillsData.get(0)));
        Assert.assertTrue(entity.getExpectedskills().contains(expectedskillsData.get(1)));
        Assert.assertTrue(entity.getExpectedskills().contains(expectedskillsData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeExpectedskillsTest() {
        serviceRequestLogic.removeExpectedskills(data.get(0).getId(), expectedskillsData.get(0).getId());
        SkillEntity response = serviceRequestLogic.getExpectedskills(data.get(0).getId(), expectedskillsData.get(0).getId());
        Assert.assertNull(response);
    }

}
