package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.ICategoryLogic;
import co.edu.uniandes.csw.homeservices.entities.CategoryEntity;
import co.edu.uniandes.csw.homeservices.persistence.CategoryPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class CategoryLogic implements ICategoryLogic {

    @Inject private CategoryPersistence persistence;

    /**
     * @generated
     */
    @Override
    public int countCategorys() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<CategoryEntity> getCategorys() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<CategoryEntity> getCategorys(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public CategoryEntity getCategory(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public CategoryEntity createCategory(CategoryEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public CategoryEntity updateCategory(CategoryEntity entity) {
        CategoryEntity newEntity = entity;
        CategoryEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteCategory(Long id) {
        persistence.delete(id);
    }
}
