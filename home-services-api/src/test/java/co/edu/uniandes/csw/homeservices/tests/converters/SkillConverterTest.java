package co.edu.uniandes.csw.homeservices.tests.converters;



import co.edu.uniandes.csw.homeservices.ejbs.SkillLogic;
import co.edu.uniandes.csw.homeservices.api.ISkillLogic;
import co.edu.uniandes.csw.homeservices.converters.SkillConverter;
import co.edu.uniandes.csw.homeservices.dtos.SkillDTO;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import co.edu.uniandes.csw.homeservices.persistence.SkillPersistence;
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
public class SkillConverterTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
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
    
    @Test
    public void refEntity2DTONullInput() {
        SkillDTO result = SkillConverter.refEntity2DTO(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void refDTO2EntityNullInput() {
        SkillDTO dto = factory.manufacturePojo(SkillDTO.class);
        SkillEntity result = SkillConverter.refDTO2Entity(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(dto.getId(), result.getId());
    }
    /**
     * For testing private method basicEntity2DTO 
     */
    @Test
    public void listEntity2DTONullInput() {
        List<SkillEntity> list= new ArrayList<>();
        list.add(null);
        List<SkillDTO> result = SkillConverter.listEntity2DTO(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    /**
     * For testing private method basicDTO2Entity
     */
    @Test
    public void listDTO2EntityNullInput() {
        List<SkillDTO> list= new ArrayList<>();
        list.add(null);
        List<SkillEntity> result = SkillConverter.listDTO2Entity(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    @Test
    public void fullEntity2DTONullInput() {
        SkillDTO result = SkillConverter.fullEntity2DTO(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void fullDTO2EntityNullInput() {
        SkillEntity result = SkillConverter.fullDTO2Entity(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void listEntity2DTONullListInput() {
        List<SkillDTO> result = SkillConverter.listEntity2DTO(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0,result.size());
    }

    @Test
    public void listDTO2EntityNullListInput() {
        List<SkillEntity> result = SkillConverter.listDTO2Entity(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}
