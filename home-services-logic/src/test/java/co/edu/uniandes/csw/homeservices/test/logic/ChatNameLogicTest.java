package co.edu.uniandes.csw.homeservices.test.logic;

import co.edu.uniandes.csw.homeservices.ejbs.ChatNameLogic;
import co.edu.uniandes.csw.homeservices.api.IChatNameLogic;
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
public class ChatNameLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IChatNameLogic chatNameLogic;

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
    private List<ChatNameEntity> data = new ArrayList<ChatNameEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ChatNameEntity.class.getPackage())
                .addPackage(ChatNameLogic.class.getPackage())
                .addPackage(IChatNameLogic.class.getPackage())
                .addPackage(ChatNamePersistence.class.getPackage())
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
        em.createQuery("delete from ChatNameEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
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
        ChatNameEntity entity = factory.manufacturePojo(ChatNameEntity.class);
        ChatNameEntity result = chatNameLogic.createChat(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void getChatNamesTest() {
        List<ChatNameEntity> list = chatNameLogic.getChats();
        Assert.assertEquals(data.size(), list.size());
        for (ChatNameEntity entity : list) {
            boolean found = false;
            for (ChatNameEntity storedEntity : data) {
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
    public void getChatNameTest() {
        ChatNameEntity entity = data.get(0);
        ChatNameEntity resultEntity = chatNameLogic.getChat(entity.getName());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * @generated
     */
    @Test
    public void deleteChatNameTest() {
        ChatNameEntity entity = data.get(1);
        chatNameLogic.deleteChat(entity.getId());
        ChatNameEntity deleted = em.find(ChatNameEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateChatNameTest() {
        ChatNameEntity entity = data.get(0);
        ChatNameEntity pojoEntity = factory.manufacturePojo(ChatNameEntity.class);

        pojoEntity.setId(entity.getId());

        chatNameLogic.updateChat(pojoEntity);

        ChatNameEntity resp = em.find(ChatNameEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}
