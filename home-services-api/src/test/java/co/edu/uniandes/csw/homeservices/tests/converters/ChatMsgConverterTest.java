package co.edu.uniandes.csw.homeservices.tests.converters;



import co.edu.uniandes.csw.homeservices.ejbs.ChatMsgLogic;
import co.edu.uniandes.csw.homeservices.api.IChatMsgLogic;
import co.edu.uniandes.csw.homeservices.converters.ChatMsgConverter;
import co.edu.uniandes.csw.homeservices.dtos.ChatMsgDTO;
import co.edu.uniandes.csw.homeservices.entities.ChatMsgEntity;
import co.edu.uniandes.csw.homeservices.persistence.ChatMsgPersistence;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


/**
 * Testing converters for improving coverage
 * @author jair
 */
@RunWith(Arquillian.class)
public class ChatMsgConverterTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
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
    
    @Test
    public void refEntity2DTONullInput() {
        ChatMsgDTO result = ChatMsgConverter.refEntity2DTO(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void refDTO2EntityNullInput() {
        ChatMsgDTO dto = factory.manufacturePojo(ChatMsgDTO.class);
        ChatMsgEntity result = ChatMsgConverter.refDTO2Entity(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(dto.getIdChatName(), result.getId());
    }
    /**
     * For testing private method basicEntity2DTO 
     */
    @Test
    public void listEntity2DTONullInput() {
        List<ChatMsgEntity> list= new ArrayList<>();
        list.add(null);
        List<ChatMsgDTO> result = ChatMsgConverter.listEntity2DTO(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    /**
     * For testing private method basicDTO2Entity
     */
    @Test
    public void listDTO2EntityNullInput() {
        List<ChatMsgDTO> list= new ArrayList<>();
        list.add(null);
        List<ChatMsgEntity> result = ChatMsgConverter.listDTO2Entity(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    @Test
    public void fullEntity2DTONullInput() {
        ChatMsgDTO result = ChatMsgConverter.fullEntity2DTO(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void fullDTO2EntityNullInput() {
        ChatMsgEntity result = ChatMsgConverter.fullDTO2Entity(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void listEntity2DTONullListInput() {
        List<ChatMsgDTO> result = ChatMsgConverter.listEntity2DTO(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0,result.size());
    }

    @Test
    public void listDTO2EntityNullListInput() {
        List<ChatMsgEntity> result = ChatMsgConverter.listDTO2Entity(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}
