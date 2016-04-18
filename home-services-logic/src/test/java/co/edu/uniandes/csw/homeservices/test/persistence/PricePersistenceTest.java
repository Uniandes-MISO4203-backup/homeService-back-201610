/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.test.persistence;

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
public class PricePersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PriceEntity.class.getPackage())
                .addPackage(PricePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private PricePersistence pricePersistence;
    
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
        em.createQuery("delete from PriceEntity").executeUpdate();
    }

    private List<PriceEntity> data = new ArrayList<PriceEntity>();

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
        PodamFactory factory = new PodamFactoryImpl();
        PriceEntity newEntity = factory.manufacturePojo(PriceEntity.class);
        PriceEntity result = pricePersistence.create(newEntity);
        Assert.assertNotNull(result);
        PriceEntity entity = em.find(PriceEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    @Test
    public void getPriceTest() {
        PriceEntity entity = data.get(0);
        PriceEntity newEntity = pricePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    @Test
    public void deletePriceTest() {
        PriceEntity entity = data.get(0);
        pricePersistence.delete(entity.getId());
        PriceEntity deleted = em.find(PriceEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updatePriceTest() {
        PriceEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PriceEntity newEntity = factory.manufacturePojo(PriceEntity.class);
        newEntity.setId(entity.getId());
        pricePersistence.update(newEntity);
        PriceEntity resp = em.find(PriceEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
    
}
