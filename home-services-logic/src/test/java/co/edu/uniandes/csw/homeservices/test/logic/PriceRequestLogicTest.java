/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.test.logic;

import co.edu.uniandes.csw.homeservices.api.IPriceRequestLogic;
import co.edu.uniandes.csw.homeservices.ejbs.PriceRequestLogic;
import co.edu.uniandes.csw.homeservices.entities.PriceRequestEntity;
import co.edu.uniandes.csw.homeservices.persistence.PriceRequestPersistence;
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
public class PriceRequestLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IPriceRequestLogic priceRequestLogic;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
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
        em.createQuery("delete from PriceRequestEntity").executeUpdate();
    }

    private List<PriceRequestEntity> data = new ArrayList<PriceRequestEntity>();

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PriceRequestEntity entity = factory.manufacturePojo(PriceRequestEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createPriceRequestTest(){
        PriceRequestEntity entity = factory.manufacturePojo(PriceRequestEntity.class);
        PriceRequestEntity result = priceRequestLogic.createPriceRequest(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
    }
    
    @Test
    public void getPricesRequestTest() {
       List<PriceRequestEntity> list = priceRequestLogic.getPriceRequests();
        Assert.assertEquals(data.size(), list.size());
        for (PriceRequestEntity entity : list) {
            boolean found = false;
            for (PriceRequestEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     @Test
    public void getPriceRequestTest() {
        PriceRequestEntity entity = data.get(0);
        PriceRequestEntity resultEntity = priceRequestLogic.getPriceRequest(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    @Test
    public void deletePriceRequestTest() {
        PriceRequestEntity entity = data.get(1);
        priceRequestLogic.deletePriceRequest(entity.getId());
        PriceRequestEntity deleted = em.find(PriceRequestEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updatePriceRequestTest() {
        PriceRequestEntity entity = data.get(0);
        PriceRequestEntity pojoEntity = factory.manufacturePojo(PriceRequestEntity.class);
        pojoEntity.setId(entity.getId());
        priceRequestLogic.updatePriceRequest(pojoEntity);
        PriceRequestEntity resp = em.find(PriceRequestEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
        
    @Test
    public void getByServiceRequest() {
        List<PriceRequestEntity> result = priceRequestLogic.getByServiceRequest(1+1L);
        Assert.assertEquals(0, result.size());
    }
}
