package co.edu.uniandes.csw.homeservices.test.logic;

import co.edu.uniandes.csw.homeservices.ejbs.CategoryLogic;
import co.edu.uniandes.csw.homeservices.api.ICategoryLogic;
import co.edu.uniandes.csw.homeservices.entities.CategoryEntity;
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
 * @generated
 */
@RunWith(Arquillian.class)
public class CategoryLogicTest {

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private ICategoryLogic categoryLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<CategoryEntity> data = new ArrayList<CategoryEntity>();

    /**
     * @generated
     */
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

    /**
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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

    /**
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from CategoryEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CategoryEntity entity = factory.manufacturePojo(CategoryEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createCategoryTest() {
        CategoryEntity entity = factory.manufacturePojo(CategoryEntity.class);
        CategoryEntity result = categoryLogic.createCategory(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getDescription(), entity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void getCategorysTest() {
        List<CategoryEntity> list = categoryLogic.getCategorys();
        Assert.assertEquals(data.size(), list.size());
        for (CategoryEntity entity : list) {
            boolean found = false;
            for (CategoryEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * @generated
     */
    @Test
    public void getCategoryTest() {
        CategoryEntity entity = data.get(0);
        CategoryEntity resultEntity = categoryLogic.getCategory(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void deleteCategoryTest() {
        CategoryEntity entity = data.get(1);
        categoryLogic.deleteCategory(entity.getId());
        CategoryEntity deleted = em.find(CategoryEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateCategoryTest() {
        CategoryEntity entity = data.get(0);
        CategoryEntity pojoEntity = factory.manufacturePojo(CategoryEntity.class);

        pojoEntity.setId(entity.getId());

        categoryLogic.updateCategory(pojoEntity);

        CategoryEntity resp = em.find(CategoryEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
    }
}
