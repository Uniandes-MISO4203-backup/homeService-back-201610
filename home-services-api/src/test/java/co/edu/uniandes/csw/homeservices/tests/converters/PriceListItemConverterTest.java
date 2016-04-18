/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.tests.converters;

import co.edu.uniandes.csw.homeservices.ejbs.CategoryLogic;
import co.edu.uniandes.csw.homeservices.api.ICategoryLogic;
import co.edu.uniandes.csw.homeservices.converters.PriceListItemConverter;
import co.edu.uniandes.csw.homeservices.dtos.PriceListItemDTO;
import co.edu.uniandes.csw.homeservices.entities.CategoryEntity;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.entities.PriceRequestEntity;
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
 *
 * @author j.garcia100
 */
@RunWith(Arquillian.class)
public class PriceListItemConverterTest {
     
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
    public void fullEntity2DTO() {
        PriceRequestEntity entity = factory.manufacturePojo(PriceRequestEntity.class);
        entity.setContractor(factory.manufacturePojo(ContractorEntity.class));
        PriceListItemDTO result = PriceListItemConverter.fullEntity2DTO(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getId(), result.getId());
    }
    
    @Test
    public void listEntity2DTONullInput() {
        List<PriceRequestEntity> list= new ArrayList<>();
        list.add(null);
        List<PriceListItemDTO> result = PriceListItemConverter.listEntity2DTO(list);
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(0));
    }
    
    @Test
    public void listEntity2DTONullListInput() {
        List<PriceListItemDTO> result = PriceListItemConverter.listEntity2DTO(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0,result.size());
    }
    
    @Test
    public void listEntity2DTOListInput() {
        List<PriceRequestEntity> list= new ArrayList<>();
        PriceRequestEntity entity = factory.manufacturePojo(PriceRequestEntity.class);
        entity.setContractor(factory.manufacturePojo(ContractorEntity.class));
        list.add(entity);
        List<PriceListItemDTO> result = PriceListItemConverter.listEntity2DTO(list);
        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getId(),result.get(0).getId());
    }
}
