/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.test.logic;

import co.edu.uniandes.csw.homeservices.api.IPriceLogic;
import co.edu.uniandes.csw.homeservices.ejbs.PriceLogic;
import co.edu.uniandes.csw.homeservices.entities.PriceEntity;
import co.edu.uniandes.csw.homeservices.persistence.PricePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
@RunWith(Arquillian.class)
public class PriceLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IPriceLogic priceLogic;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<PriceEntity> data = new ArrayList<PriceEntity>();
    
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PriceEntity.class.getPackage())
                .addPackage(PriceLogic.class.getPackage())
                .addPackage(IPriceLogic.class.getPackage())
                .addPackage(PricePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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
    

    private void clearData() {
        em.createQuery("delete from PriceEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PriceEntity entity = factory.manufacturePojo(PriceEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createPriceTest(){
        PriceEntity entity = factory.manufacturePojo(PriceEntity.class);
        PriceEntity result = priceLogic.createPrice(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getDescription(), entity.getDescription());
    }
    
    @Test
    public void getPricesTest() {
        List<PriceEntity> list = priceLogic.getPrices();
        Assert.assertEquals(data.size(), list.size());
        for (PriceEntity entity : list) {
            boolean found = false;
            for (PriceEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getPriceTest() {
        PriceEntity entity = data.get(0);
        PriceEntity resultEntity = priceLogic.getPrice(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
    }

    @Test
    public void deletePriceTest() {
        PriceEntity entity = data.get(1);
        priceLogic.deletePrice(entity.getId());
        PriceEntity deleted = em.find(PriceEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updatePriceTest() {
        PriceEntity entity = data.get(0);
        PriceEntity pojoEntity = factory.manufacturePojo(PriceEntity.class);
        pojoEntity.setId(entity.getId());
        priceLogic.updatePrice(pojoEntity);
        PriceEntity resp = em.find(PriceEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
    }
    
}
