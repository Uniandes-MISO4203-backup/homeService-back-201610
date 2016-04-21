package co.edu.uniandes.csw.homeservices.test.logic;

import co.edu.uniandes.csw.homeservices.ejbs.StatusLogic;
import co.edu.uniandes.csw.homeservices.api.IStatusLogic;
import co.edu.uniandes.csw.homeservices.entities.StatusEntity;
import co.edu.uniandes.csw.homeservices.persistence.StatusPersistence;
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
public class StatusLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IStatusLogic statusLogic;

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
    private List<StatusEntity> data = new ArrayList<StatusEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(StatusEntity.class.getPackage())
                .addPackage(StatusLogic.class.getPackage())
                .addPackage(IStatusLogic.class.getPackage())
                .addPackage(StatusPersistence.class.getPackage())
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
        em.createQuery("delete from StatusEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            StatusEntity entity = factory.manufacturePojo(StatusEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createStatusTest() {
        StatusEntity entity = factory.manufacturePojo(StatusEntity.class);
        StatusEntity result = statusLogic.createStatus(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getStatussTest() {
        List<StatusEntity> list = statusLogic.getStatuss();
        Assert.assertEquals(data.size(), list.size());
        for (StatusEntity entity : list) {
            boolean found = false;
            for (StatusEntity storedEntity : data) {
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
    public void getStatusTest() {
        StatusEntity entity = data.get(0);
        StatusEntity resultEntity = statusLogic.getStatus(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteStatusTest() {
        StatusEntity entity = data.get(1);
        statusLogic.deleteStatus(entity.getId());
        StatusEntity deleted = em.find(StatusEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateStatusTest() {
        StatusEntity entity = data.get(0);
        StatusEntity pojoEntity = factory.manufacturePojo(StatusEntity.class);

        pojoEntity.setId(entity.getId());

        statusLogic.updateStatus(pojoEntity);

        StatusEntity resp = em.find(StatusEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}
