package co.edu.uniandes.csw.homeservices.test.logic;

import co.edu.uniandes.csw.homeservices.ejbs.SkillLogic;
import co.edu.uniandes.csw.homeservices.api.ISkillLogic;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import co.edu.uniandes.csw.homeservices.persistence.SkillPersistence;
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
public class SkillLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ISkillLogic skillLogic;

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
    private List<SkillEntity> data = new ArrayList<SkillEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SkillEntity.class.getPackage())
                .addPackage(SkillLogic.class.getPackage())
                .addPackage(ISkillLogic.class.getPackage())
                .addPackage(SkillPersistence.class.getPackage())
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
        em.createQuery("delete from SkillEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SkillEntity entity = factory.manufacturePojo(SkillEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createSkillTest() {
        SkillEntity entity = factory.manufacturePojo(SkillEntity.class);
        SkillEntity result = skillLogic.createSkill(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getDescription(), entity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void getSkillsTest() {
        List<SkillEntity> list = skillLogic.getSkills();
        Assert.assertEquals(data.size(), list.size());
        for (SkillEntity entity : list) {
            boolean found = false;
            for (SkillEntity storedEntity : data) {
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
    public void getSkillTest() {
        SkillEntity entity = data.get(0);
        SkillEntity resultEntity = skillLogic.getSkill(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void deleteSkillTest() {
        SkillEntity entity = data.get(1);
        skillLogic.deleteSkill(entity.getId());
        SkillEntity deleted = em.find(SkillEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateSkillTest() {
        SkillEntity entity = data.get(0);
        SkillEntity pojoEntity = factory.manufacturePojo(SkillEntity.class);

        pojoEntity.setId(entity.getId());

        skillLogic.updateSkill(pojoEntity);

        SkillEntity resp = em.find(SkillEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
    }
}
