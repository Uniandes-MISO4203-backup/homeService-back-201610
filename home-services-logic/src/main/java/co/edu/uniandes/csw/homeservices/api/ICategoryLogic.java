package co.edu.uniandes.csw.homeservices.api;

import co.edu.uniandes.csw.homeservices.entities.CategoryEntity;
import java.util.List;

public interface ICategoryLogic {
    public int countCategorys();
    public List<CategoryEntity> getCategorys();
    public List<CategoryEntity> getCategorys(Integer page, Integer maxRecords);
    public CategoryEntity getCategory(Long id);
    public CategoryEntity createCategory(CategoryEntity entity);
    public CategoryEntity updateCategory(CategoryEntity entity);
    public void deleteCategory(Long id);
}
