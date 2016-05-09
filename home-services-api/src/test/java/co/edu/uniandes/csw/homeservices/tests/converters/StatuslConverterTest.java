package co.edu.uniandes.csw.homeservices.tests.converters;



import co.edu.uniandes.csw.homeservices.ejbs.StatusLogic;
import co.edu.uniandes.csw.homeservices.api.IStatusLogic;
import co.edu.uniandes.csw.homeservices.converters.StatusConverter;
import co.edu.uniandes.csw.homeservices.dtos.StatusDTO;
import co.edu.uniandes.csw.homeservices.entities.StatusEntity;
import co.edu.uniandes.csw.homeservices.entities.StatusEntity;
import co.edu.uniandes.csw.homeservices.persistence.StatusPersistence;
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
public class StatuslConverterTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
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
    
    @Test
    public void refEntity2DTONullInput() {
        StatusDTO result = StatusConverter.refEntity2DTO(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void refDTO2EntityNullInput() {
        StatusDTO dto = factory.manufacturePojo(StatusDTO.class);
        StatusEntity result = StatusConverter.refDTO2Entity(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(dto.getId(), result.getId());
    }
    /**
     * For testing private method basicEntity2DTO 
     */
    @Test
    public void listEntity2DTONullInput() {
        List<StatusEntity> list= new ArrayList<>();
        list.add(null);
        List<StatusDTO> result = StatusConverter.listEntity2DTO(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    /**
     * For testing private method basicDTO2Entity
     */
    @Test
    public void listDTO2EntityNullInput() {
        List<StatusDTO> list= new ArrayList<>();
        list.add(null);
        List<StatusEntity> result = StatusConverter.listDTO2Entity(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    @Test
    public void fullEntity2DTONullInput() {
        StatusDTO result = StatusConverter.fullEntity2DTO(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void fullDTO2EntityNullInput() {
        StatusEntity result = StatusConverter.fullDTO2Entity(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void listEntity2DTONullListInput() {
        List<StatusDTO> result = StatusConverter.listEntity2DTO(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0,result.size());
    }

    @Test
    public void listDTO2EntityNullListInput() {
        List<StatusEntity> result = StatusConverter.listDTO2Entity(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}
