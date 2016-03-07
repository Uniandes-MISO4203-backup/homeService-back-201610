package co.edu.uniandes.csw.homeservices.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @generated
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Service.getByDescription", query = "select u from ServiceRequestEntity u JOIN u.customer c1 Where UPPER(u.description) like :description and c1.id= :customerId")
})   
public class ServiceRequestEntity extends BaseEntity implements Serializable {

    private Integer price;

    private String recommendedTime;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date dueDate;

    @PodamExclude
    @ManyToMany
    private List<SkillEntity> expectedskills = new ArrayList<>();

    @PodamExclude
    @ManyToOne
    private CategoryEntity category;

    @PodamExclude
    @ManyToOne
    private StatusEntity status;

    @PodamExclude
    @ManyToOne
    private CustomerEntity customer;
    
    private String description;
    
    
    

    /**
     * @generated
     */
    public Integer getPrice(){
        return price;
    }

    /**
     * @generated
     */
    public void setPrice(Integer price){
        this.price = price;
    }

    /**
     * @generated
     */
    public String getRecommendedTime(){
        return recommendedTime;
    }

    /**
     * @generated
     */
    public void setRecommendedTime(String recommendedTime){
        this.recommendedTime = recommendedTime;
    }

    /**
     * @generated
     */
    public Date getCreationDate(){
        return creationDate;
    }

    /**
     * @generated
     */
    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }

    /**
     * @generated
     */
    public Date getDueDate(){
        return dueDate;
    }

    /**
     * @generated
     */
    public void setDueDate(Date dueDate){
        this.dueDate = dueDate;
    }

    /**
     * @generated
     */
    public CategoryEntity getCategory() {
        return category;
    }

    /**
     * @generated
     */
    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    /**
     * @generated
     */
    public CustomerEntity getCustomer() {
        return customer;
    }

    /**
     * @generated
     */
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    /**
     * @generated
     */
    public StatusEntity getStatus() {
        return status;
    }

    /**
     * @generated
     */
    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    /**
     * @generated
     */
    public List<SkillEntity> getExpectedskills() {
        return expectedskills;
    }

    /**
     * @generated
     */
    public void setExpectedskills(List<SkillEntity> expectedskills) {
        this.expectedskills = expectedskills;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

   
}
