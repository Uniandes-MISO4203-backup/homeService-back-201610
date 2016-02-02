package co.edu.uniandes.csw.homeservices.test.persistence;

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
public class StatusPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(StatusEntity.class.getPackage())
                .addPackage(StatusPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private StatusPersistence statusPersistence;

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
        em.createQuery("delete from StatusEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<StatusEntity> data = new ArrayList<StatusEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
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
		PodamFactory factory = new PodamFactoryImpl();
        StatusEntity newEntity = factory.manufacturePojo(StatusEntity.class);
        StatusEntity result = statusPersistence.create(newEntity);

        Assert.assertNotNull(result);

        StatusEntity entity = em.find(StatusEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getStatussTest() {
        List<StatusEntity> list = statusPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (StatusEntity ent : list) {
            boolean found = false;
            for (StatusEntity entity : data) {
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
    public void getStatusTest() {
        StatusEntity entity = data.get(0);
        StatusEntity newEntity = statusPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteStatusTest() {
        StatusEntity entity = data.get(0);
        statusPersistence.delete(entity.getId());
        StatusEntity deleted = em.find(StatusEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateStatusTest() {
        StatusEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        StatusEntity newEntity = factory.manufacturePojo(StatusEntity.class);

        newEntity.setId(entity.getId());

        statusPersistence.update(newEntity);

        StatusEntity resp = em.find(StatusEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
