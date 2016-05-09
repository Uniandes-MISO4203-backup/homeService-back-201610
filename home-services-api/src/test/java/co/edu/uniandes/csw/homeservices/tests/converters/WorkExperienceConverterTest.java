package co.edu.uniandes.csw.homeservices.tests.converters;

import co.edu.uniandes.csw.homeservices.converters.WorkExperienceConverter;
import co.edu.uniandes.csw.homeservices.dtos.WorkExperienceDTO;
import co.edu.uniandes.csw.homeservices.entities.WorkExperienceEntity;
import co.edu.uniandes.csw.homeservices.entities.WorkExperienceEntity;
import co.edu.uniandes.csw.homeservices.persistence.WorkExperiencePersistence;
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
public class WorkExperienceConverterTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(WorkExperienceEntity.class.getPackage())
                .addPackage(WorkExperiencePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void refEntity2DTONullInput() {
        WorkExperienceDTO result = WorkExperienceConverter.refEntity2DTO(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void refDTO2EntityNullInput() {
        WorkExperienceDTO dto = factory.manufacturePojo(WorkExperienceDTO.class);
        WorkExperienceEntity result = WorkExperienceConverter.refDTO2Entity(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(dto.getId(), result.getId());
    }
    /**
     * For testing private method basicEntity2DTO 
     */
    @Test
    public void listEntity2DTONullInput() {
        List<WorkExperienceEntity> list= new ArrayList<>();
        list.add(null);
        List<WorkExperienceDTO> result = WorkExperienceConverter.listEntity2DTO(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    /**
     * For testing private method basicDTO2Entity
     */
    @Test
    public void listDTO2EntityNullInput() {
        List<WorkExperienceDTO> list= new ArrayList<>();
        list.add(null);
        List<WorkExperienceEntity> result = WorkExperienceConverter.listDTO2Entity(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    @Test
    public void fullEntity2DTONullInput() {
        WorkExperienceDTO result = WorkExperienceConverter.fullEntity2DTO(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void fullDTO2EntityNullInput() {
        WorkExperienceEntity result = WorkExperienceConverter.fullDTO2Entity(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void listEntity2DTONullListInput() {
        List<WorkExperienceDTO> result = WorkExperienceConverter.listEntity2DTO(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0,result.size());
    }

    @Test
    public void listDTO2EntityNullListInput() {
        List<WorkExperienceEntity> result = WorkExperienceConverter.listDTO2Entity(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}
