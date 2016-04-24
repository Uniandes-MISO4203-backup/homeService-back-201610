package co.edu.uniandes.csw.homeservices.test.logic;

import co.edu.uniandes.csw.homeservices.ejbs.ChatMsgLogic;
import co.edu.uniandes.csw.homeservices.api.IChatMsgLogic;
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
public class ChatMsgLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IChatMsgLogic chatMsgLogic;

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
    private List<ChatMsgEntity> data = new ArrayList<ChatMsgEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ChatMsgEntity.class.getPackage())
                .addPackage(ChatMsgLogic.class.getPackage())
                .addPackage(IChatMsgLogic.class.getPackage())
                .addPackage(ChatMsgPersistence.class.getPackage())
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
        em.createQuery("delete from ChatMsgEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
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
        ChatMsgEntity entity = factory.manufacturePojo(ChatMsgEntity.class);
        ChatMsgEntity result = chatMsgLogic.createChatMsg(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getChatMsgsTest() {
        List<ChatMsgEntity> list = chatMsgLogic.getChatMsgs();
        Assert.assertEquals(data.size(), list.size());
        for (ChatMsgEntity entity : list) {
            boolean found = false;
            for (ChatMsgEntity storedEntity : data) {
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
    public void getChatMsgTest() {
        ChatMsgEntity entity = data.get(0);
        ChatMsgEntity resultEntity = chatMsgLogic.getChatMsg(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteChatMsgTest() {
        ChatMsgEntity entity = data.get(1);
        chatMsgLogic.deleteChatMsg(entity.getId());
        ChatMsgEntity deleted = em.find(ChatMsgEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateChatMsgTest() {
        ChatMsgEntity entity = data.get(0);
        ChatMsgEntity pojoEntity = factory.manufacturePojo(ChatMsgEntity.class);

        pojoEntity.setId(entity.getId());

        chatMsgLogic.updateChatMsg(pojoEntity);

        ChatMsgEntity resp = em.find(ChatMsgEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}
