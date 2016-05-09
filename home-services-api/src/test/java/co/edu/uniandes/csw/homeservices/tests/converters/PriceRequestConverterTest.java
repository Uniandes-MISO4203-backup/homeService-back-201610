package co.edu.uniandes.csw.homeservices.tests.converters;



import co.edu.uniandes.csw.homeservices.ejbs.PriceRequestLogic;
import co.edu.uniandes.csw.homeservices.api.IPriceRequestLogic;
import co.edu.uniandes.csw.homeservices.converters.PriceRequestConverter;
import co.edu.uniandes.csw.homeservices.dtos.PriceRequestDTO;
import co.edu.uniandes.csw.homeservices.entities.PriceRequestEntity;
import co.edu.uniandes.csw.homeservices.persistence.PriceRequestPersistence;
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
public class PriceRequestConverterTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PriceRequestEntity.class.getPackage())
                .addPackage(PriceRequestLogic.class.getPackage())
                .addPackage(IPriceRequestLogic.class.getPackage())
                .addPackage(PriceRequestPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void refEntity2DTONullInput() {
        PriceRequestDTO result = PriceRequestConverter.refEntity2DTO(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void refDTO2EntityNullInput() {
        PriceRequestDTO dto = factory.manufacturePojo(PriceRequestDTO.class);
        PriceRequestEntity result = PriceRequestConverter.refDTO2Entity(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(dto.getId(), result.getId());
    }
    /**
     * For testing private method basicEntity2DTO 
     */
    @Test
    public void listEntity2DTONullInput() {
        List<PriceRequestEntity> list= new ArrayList<>();
        list.add(null);
        List<PriceRequestDTO> result = PriceRequestConverter.listEntity2DTO(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    /**
     * For testing private method basicDTO2Entity
     */
    @Test
    public void listDTO2EntityNullInput() {
        List<PriceRequestDTO> list= new ArrayList<>();
        list.add(null);
        List<PriceRequestEntity> result = PriceRequestConverter.listDTO2Entity(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    @Test
    public void fullEntity2DTONullInput() {
        PriceRequestDTO result = PriceRequestConverter.fullEntity2DTO(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void fullDTO2EntityNullInput() {
        PriceRequestEntity result = PriceRequestConverter.fullDTO2Entity(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void listEntity2DTONullListInput() {
        List<PriceRequestDTO> result = PriceRequestConverter.listEntity2DTO(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0,result.size());
    }

    @Test
    public void listDTO2EntityNullListInput() {
        List<PriceRequestEntity> result = PriceRequestConverter.listDTO2Entity(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}
