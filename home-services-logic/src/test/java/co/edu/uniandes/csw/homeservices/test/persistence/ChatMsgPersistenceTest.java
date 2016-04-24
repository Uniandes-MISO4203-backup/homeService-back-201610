package co.edu.uniandes.csw.homeservices.test.persistence;

import co.edu.uniandes.csw.homeservices.entities.ChatMsgEntity;
import co.edu.uniandes.csw.homeservices.persistence.ChatMsgPersistence;
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
public class ChatMsgPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ChatMsgEntity.class.getPackage())
                .addPackage(ChatMsgPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private ChatMsgPersistence chatMsgPersistence;

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
        em.createQuery("delete from ChatMsgEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<ChatMsgEntity> data = new ArrayList<ChatMsgEntity>();

    /**
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ChatMsgEntity entity = factory.manufacturePojo(ChatMsgEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createChatMsgTest() {
		PodamFactory factory = new PodamFactoryImpl();
        ChatMsgEntity newEntity = factory.manufacturePojo(ChatMsgEntity.class);
        ChatMsgEntity result = chatMsgPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ChatMsgEntity entity = em.find(ChatMsgEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getChatMsgsTest() {
        List<ChatMsgEntity> list = chatMsgPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ChatMsgEntity ent : list) {
            boolean found = false;
            for (ChatMsgEntity entity : data) {
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
    public void getChatMsgTest() {
        ChatMsgEntity entity = data.get(0);
        ChatMsgEntity newEntity = chatMsgPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteChatMsgTest() {
        ChatMsgEntity entity = data.get(0);
        chatMsgPersistence.delete(entity.getId());
        ChatMsgEntity deleted = em.find(ChatMsgEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateChatMsgTest() {
        ChatMsgEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ChatMsgEntity newEntity = factory.manufacturePojo(ChatMsgEntity.class);

        newEntity.setId(entity.getId());

        chatMsgPersistence.update(newEntity);

        ChatMsgEntity resp = em.find(ChatMsgEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}