package co.edu.uniandes.csw.homeservices.tests.converters;



import co.edu.uniandes.csw.homeservices.ejbs.CategoryLogic;
import co.edu.uniandes.csw.homeservices.api.ICategoryLogic;
import co.edu.uniandes.csw.homeservices.converters.CustomerConverter;
import co.edu.uniandes.csw.homeservices.dtos.CustomerDTO;
import co.edu.uniandes.csw.homeservices.entities.CategoryEntity;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import co.edu.uniandes.csw.homeservices.persistence.CategoryPersistence;
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
 * Testing converters for improving coverage
 * @author jair
 */
@RunWith(Arquillian.class)
public class CustomerConverterTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    private List<CategoryEntity> data = new ArrayList<CategoryEntity>();
    
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
    public void refDTO2EntityNullInput() {
        CustomerDTO dto = factory.manufacturePojo(CustomerDTO.class);
        CustomerEntity result = CustomerConverter.refDTO2Entity(dto);
        Assert.assertNotNull(result);
        Assert.assertEquals(dto.getId(), result.getId());
    }
    /**
     * For testing private method basicEntity2DTO 
     */
    @Test
    public void listEntity2DTONullInput() {
        List<CustomerEntity> list= new ArrayList<>();
        list.add(null);
        List<CustomerDTO> result = CustomerConverter.listEntity2DTO(list);
        Assert.assertNotNull(result);
        Assert.assertNull(list.get(0));
    }
    
    /**
     * For testing private method basicDTO2Entity
     */
    @Test
    public void listDTO2EntityNullInput() {
        List<CustomerDTO> list= new ArrayList<>();
        list.add(null);
        List<CustomerEntity> result = CustomerConverter.listDTO2Entity(list);
        Assert.assertNotNull(result);
        Assert.assertNull(list.get(0));
    }
    
    @Test
    public void fullEntity2DTONullInput() {
        CustomerDTO result = CustomerConverter.fullEntity2DTO(null);
        Assert.assertNull(result);
    }
    
    @Test
    public void fullDTO2EntityNullInput() {
        CustomerEntity result = CustomerConverter.fullDTO2Entity(null);
        Assert.assertNull(result);
    }
    
}
