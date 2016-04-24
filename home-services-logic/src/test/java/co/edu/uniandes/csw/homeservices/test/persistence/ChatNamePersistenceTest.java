package co.edu.uniandes.csw.homeservices.test.persistence;

import co.edu.uniandes.csw.homeservices.entities.ChatNameEntity;
import co.edu.uniandes.csw.homeservices.persistence.ChatNamePersistence;
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
public class ChatNamePersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ChatNameEntity.class.getPackage())
                .addPackage(ChatNamePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private ChatNamePersistence chatNamePersistence;

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
        em.createQuery("delete from ChatNameEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<ChatNameEntity> data = new ArrayList<ChatNameEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ChatNameEntity entity = factory.manufacturePojo(ChatNameEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createChatNameTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ChatNameEntity newEntity = factory.manufacturePojo(ChatNameEntity.class);
        ChatNameEntity result = chatNamePersistence.create(newEntity);

        Assert.assertNotNull(result);

        ChatNameEntity entity = em.find(ChatNameEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCreationDate(), entity.getCreationDate());
    }

    /**
     * @generated
     */
    @Test
    public void getChatNamesTest() {
        List<ChatNameEntity> list = chatNamePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ChatNameEntity ent : list) {
            boolean found = false;
            for (ChatNameEntity entity : data) {
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
    public void getChatNameTest() {
        ChatNameEntity entity = data.get(0);
        ChatNameEntity newEntity = chatNamePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getCreationDate(), newEntity.getCreationDate());
    }

    /**
     * @generated
     */
    @Test
    public void deleteChatNameTest() {
        ChatNameEntity entity = data.get(0);
        chatNamePersistence.delete(entity.getId());
        ChatNameEntity deleted = em.find(ChatNameEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateChatNameTest() {
        ChatNameEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ChatNameEntity newEntity = factory.manufacturePojo(ChatNameEntity.class);

        newEntity.setId(entity.getId());

        chatNamePersistence.update(newEntity);

        ChatNameEntity resp = em.find(ChatNameEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getCreationDate(), resp.getCreationDate());
    }
}
