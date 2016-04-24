/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.test.persistence;

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
public class PriceRequestPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PriceRequestEntity.class.getPackage())
                .addPackage(PriceRequestPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private PriceRequestPersistence priceRequestPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

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
        PodamFactory factory = new PodamFactoryImpl();
        PriceRequestEntity newEntity = factory.manufacturePojo(PriceRequestEntity.class);
        PriceRequestEntity result = priceRequestPersistence.create(newEntity);
        Assert.assertNotNull(result);
        PriceRequestEntity entity = em.find(PriceRequestEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    @Test
    public void getPriceRequestTest() {
        PriceRequestEntity entity = data.get(0);
        PriceRequestEntity newEntity = priceRequestPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    @Test
    public void deletePriceRequestTest() {
        PriceRequestEntity entity = data.get(0);
        priceRequestPersistence.delete(entity.getId());
        PriceRequestEntity deleted = em.find(PriceRequestEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updatePriceRequestTest() {
        PriceRequestEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PriceRequestEntity newEntity = factory.manufacturePojo(PriceRequestEntity.class);
        newEntity.setId(entity.getId());
        priceRequestPersistence.update(newEntity);
        PriceRequestEntity resp = em.find(PriceRequestEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
    @Test
    public void getByCustomerTest() {
        List<PriceRequestEntity> entityList = priceRequestPersistence.getByCustomer(1+1L);
        Assert.assertEquals(0,entityList.size());
    }
    
    @Test
    public void getByServiceRequest() {
        List<PriceRequestEntity> entityList = priceRequestPersistence.getByServiceRequest(1+1L);
        Assert.assertEquals(0,entityList.size());
    }
    
}
