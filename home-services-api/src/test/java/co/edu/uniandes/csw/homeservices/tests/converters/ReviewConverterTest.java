package co.edu.uniandes.csw.homeservices.tests.converters;



import co.edu.uniandes.csw.homeservices.ejbs.ReviewLogic;
import co.edu.uniandes.csw.homeservices.api.IReviewLogic;
import co.edu.uniandes.csw.homeservices.converters.ReviewConverter;
import co.edu.uniandes.csw.homeservices.dtos.ReviewDTO;
import co.edu.uniandes.csw.homeservices.entities.ReviewEntity;
import co.edu.uniandes.csw.homeservices.entities.ReviewEntity;
import co.edu.uniandes.csw.homeservices.persistence.ReviewPersistence;
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
public class ReviewConverterTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReviewEntity.class.getPackage())
                .addPackage(ReviewLogic.class.getPackage())
                .addPackage(IReviewLogic.class.getPackage())
                .addPackage(ReviewPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * For testing private method basicEntity2DTO 
     */
    @Test
    public void listEntity2DTONullInput() {
        List<ReviewEntity> list= new ArrayList<>();
        list.add(null);
        List<ReviewDTO> result = ReviewConverter.listEntity2DTO(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    /**
     * For testing private method basicDTO2Entity
     */
    @Test
    public void listDTO2EntityNullInput() {
        List<ReviewDTO> list= new ArrayList<>();
        list.add(null);
        List<ReviewEntity> result = ReviewConverter.listDTO2Entity(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    @Test
    public void listEntity2DTONullListInput() {
        List<ReviewDTO> result = ReviewConverter.listEntity2DTO(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0,result.size());
    }

    @Test
    public void listDTO2EntityNullListInput() {
        List<ReviewEntity> result = ReviewConverter.listDTO2Entity(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}
