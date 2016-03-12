package co.edu.uniandes.csw.homeservices.test.persistence;

import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.entities.EducationEntity;
import co.edu.uniandes.csw.homeservices.entities.WorkExperienceEntity;
import co.edu.uniandes.csw.homeservices.persistence.ContractorPersistence;
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
public class ContractorPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ContractorEntity.class.getPackage())
                .addPackage(ContractorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private ContractorPersistence contractorPersistence;

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
        em.createQuery("delete from EducationEntity").executeUpdate();
        em.createQuery("delete from WorkExperienceEntity").executeUpdate();
        em.createQuery("delete from ContractorEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<ContractorEntity> data = new ArrayList<ContractorEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ContractorEntity entity = factory.manufacturePojo(ContractorEntity.class);
            for (WorkExperienceEntity item : entity.getWorkExperiences()) {
                item.setContractor(entity);
            }
            for (EducationEntity educationEntity : entity.getEducations()) {
                educationEntity.setContractor(entity);
            }
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createContractorTest() {
		PodamFactory factory = new PodamFactoryImpl();
        ContractorEntity newEntity = factory.manufacturePojo(ContractorEntity.class);
        ContractorEntity result = contractorPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ContractorEntity entity = em.find(ContractorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getLastName(), entity.getLastName());
        Assert.assertEquals(newEntity.getDocument(), entity.getDocument());
        Assert.assertEquals(newEntity.getPicture(), entity.getPicture());
    }

    /**
     * @generated
     */
    @Test
    public void getContractorsTest() {
        List<ContractorEntity> list = contractorPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ContractorEntity ent : list) {
            boolean found = false;
            for (ContractorEntity entity : data) {
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
    public void getContractorTest() {
        ContractorEntity entity = data.get(0);
        ContractorEntity newEntity = contractorPersistence.find(entity.getId());
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
    public void deleteContractorTest() {
        ContractorEntity entity = data.get(0);
        contractorPersistence.delete(entity.getId());
        ContractorEntity deleted = em.find(ContractorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateContractorTest() {
        ContractorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ContractorEntity newEntity = factory.manufacturePojo(ContractorEntity.class);

        newEntity.setId(entity.getId());

        contractorPersistence.update(newEntity);

        ContractorEntity resp = em.find(ContractorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getLastName(), resp.getLastName());
        Assert.assertEquals(newEntity.getDocument(), resp.getDocument());
        Assert.assertEquals(newEntity.getPicture(), resp.getPicture());
    }
}
