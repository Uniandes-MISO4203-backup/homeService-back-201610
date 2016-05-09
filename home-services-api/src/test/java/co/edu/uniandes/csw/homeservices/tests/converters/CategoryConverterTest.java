package co.edu.uniandes.csw.homeservices.tests.converters;



import co.edu.uniandes.csw.homeservices.ejbs.CategoryLogic;
import co.edu.uniandes.csw.homeservices.api.ICategoryLogic;
import co.edu.uniandes.csw.homeservices.converters.CategoryConverter;
import co.edu.uniandes.csw.homeservices.dtos.CategoryDTO;
import co.edu.uniandes.csw.homeservices.entities.CategoryEntity;
import co.edu.uniandes.csw.homeservices.entities.CategoryEntity;
import co.edu.uniandes.csw.homeservices.persistence.CategoryPersistence;
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
public class CategoryConverterTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CategoryEntity.class.getPackage())
                .addPackage(CategoryLogic.class.getPackage())
                .addPackage(ICategoryLogic.class.getPackage())
                .addPackage(CategoryPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void refEntity2DTONullInput() {
        CategoryDTO result = CategoryConverter.refEntity2DTO(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void refDTO2EntityNullInput() {
        CategoryDTO dto = factory.manufacturePojo(CategoryDTO.class);
        CategoryEntity result = CategoryConverter.refDTO2Entity(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(dto.getId(), result.getId());
    }
    /**
     * For testing private method basicEntity2DTO 
     */
    @Test
    public void listEntity2DTONullInput() {
        List<CategoryEntity> list= new ArrayList<>();
        list.add(null);
        List<CategoryDTO> result = CategoryConverter.listEntity2DTO(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    /**
     * For testing private method basicDTO2Entity
     */
    @Test
    public void listDTO2EntityNullInput() {
        List<CategoryDTO> list= new ArrayList<>();
        list.add(null);
        List<CategoryEntity> result = CategoryConverter.listDTO2Entity(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    @Test
    public void fullEntity2DTONullInput() {
        CategoryDTO result = CategoryConverter.fullEntity2DTO(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void fullDTO2EntityNullInput() {
        CategoryEntity result = CategoryConverter.fullDTO2Entity(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void listEntity2DTONullListInput() {
        List<CategoryDTO> result = CategoryConverter.listEntity2DTO(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0,result.size());
    }

    @Test
    public void listDTO2EntityNullListInput() {
        List<CategoryEntity> result = CategoryConverter.listDTO2Entity(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}
