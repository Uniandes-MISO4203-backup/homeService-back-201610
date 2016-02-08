package co.edu.uniandes.csw.homeservices.test.logic;

import co.edu.uniandes.csw.homeservices.ejbs.ContractorLogic;
import co.edu.uniandes.csw.homeservices.api.IContractorLogic;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.persistence.ContractorPersistence;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import co.edu.uniandes.csw.homeservices.entities.WorkExperienceEntity;
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
public class ContractorLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IContractorLogic contractorLogic;

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
    private List<ContractorEntity> data = new ArrayList<ContractorEntity>();

    /**
     * @generated
     */
    private List<SkillEntity> skillsData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ContractorEntity.class.getPackage())
                .addPackage(ContractorLogic.class.getPackage())
                .addPackage(IContractorLogic.class.getPackage())
                .addPackage(ContractorPersistence.class.getPackage())
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
        em.createQuery("delete from WorkExperienceEntity").executeUpdate();
        em.createQuery("delete from ContractorEntity").executeUpdate();
        em.createQuery("delete from SkillEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SkillEntity skills = factory.manufacturePojo(SkillEntity.class);
            em.persist(skills);
            skillsData.add(skills);
        }

        for (int i = 0; i < 3; i++) {
            ContractorEntity entity = factory.manufacturePojo(ContractorEntity.class);

            for (WorkExperienceEntity item : entity.getWorkExperiences()) {
                item.setContractor(entity);
            }

            entity.getSkills().add(skillsData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createContractorTest() {
        ContractorEntity entity = factory.manufacturePojo(ContractorEntity.class);
        ContractorEntity result = contractorLogic.createContractor(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getLastName(), entity.getLastName());
        Assert.assertEquals(result.getDocument(), entity.getDocument());
        Assert.assertEquals(result.getPicture(), entity.getPicture());
    }

    /**
     * @generated
     */
    @Test
    public void getContractorsTest() {
        List<ContractorEntity> list = contractorLogic.getContractors();
        Assert.assertEquals(data.size(), list.size());
        for (ContractorEntity entity : list) {
            boolean found = false;
            for (ContractorEntity storedEntity : data) {
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
    public void getContractorTest() {
        ContractorEntity entity = data.get(0);
        ContractorEntity resultEntity = contractorLogic.getContractor(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getLastName(), resultEntity.getLastName());
        Assert.assertEquals(entity.getDocument(), resultEntity.getDocument());
        Assert.assertEquals(entity.getPicture(), resultEntity.getPicture());
    }

    /**
     * @generated
     */
    @Test
    public void deleteContractorTest() {
        ContractorEntity entity = data.get(1);
        contractorLogic.deleteContractor(entity.getId());
        ContractorEntity deleted = em.find(ContractorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateContractorTest() {
        ContractorEntity entity = data.get(0);
        ContractorEntity pojoEntity = factory.manufacturePojo(ContractorEntity.class);

        pojoEntity.setId(entity.getId());

        contractorLogic.updateContractor(pojoEntity);

        ContractorEntity resp = em.find(ContractorEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getLastName(), resp.getLastName());
        Assert.assertEquals(pojoEntity.getDocument(), resp.getDocument());
        Assert.assertEquals(pojoEntity.getPicture(), resp.getPicture());
    }

    /**
     * @generated
     */
    @Test
    public void getSkillsTest() {
        ContractorEntity entity = data.get(0);
        SkillEntity skillEntity = skillsData.get(0);
        SkillEntity response = contractorLogic.getSkills(entity.getId(), skillEntity.getId());

        Assert.assertEquals(skillEntity.getId(), response.getId());
        Assert.assertEquals(skillEntity.getName(), response.getName());
        Assert.assertEquals(skillEntity.getDescription(), response.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void listSkillsTest() {
        List<SkillEntity> list = contractorLogic.listSkills(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * @generated
     */
    @Test
    public void addSkillsTest() {
        ContractorEntity entity = data.get(0);
        SkillEntity skillEntity = skillsData.get(1);
        SkillEntity response = contractorLogic.addSkills(entity.getId(), skillEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(skillEntity.getId(), response.getId());
    }

    /**
     * @generated
     */
    @Test
    public void replaceSkillsTest() {
        ContractorEntity entity = data.get(0);
        List<SkillEntity> list = skillsData.subList(1, 3);
        contractorLogic.replaceSkills(entity.getId(), list);

        entity = contractorLogic.getContractor(entity.getId());
        Assert.assertFalse(entity.getSkills().contains(skillsData.get(0)));
        Assert.assertTrue(entity.getSkills().contains(skillsData.get(1)));
        Assert.assertTrue(entity.getSkills().contains(skillsData.get(2)));
    }

    /**
     * @generated
     */
    @Test
    public void removeSkillsTest() {
        contractorLogic.removeSkills(data.get(0).getId(), skillsData.get(0).getId());
        SkillEntity response = contractorLogic.getSkills(data.get(0).getId(), skillsData.get(0).getId());
        Assert.assertNull(response);
    }
}
