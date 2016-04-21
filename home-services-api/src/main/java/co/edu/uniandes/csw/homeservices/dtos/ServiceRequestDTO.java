package co.edu.uniandes.csw.homeservices.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.Date;
import uk.co.jemos.podam.common.PodamStrategyValue;
import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@XmlRootElement
public class ServiceRequestDTO {

    private Long id;
    private String name;
    private Integer price;
    private String recommendedTime;
    
    private Integer score;
    @PodamStrategyValue(DateStrategy.class)
    private Date creationDate;
    @PodamStrategyValue(DateStrategy.class)
    private Date dueDate;
    @PodamStrategyValue(DateStrategy.class)
    private Date priceRequestLimit;
    @PodamExclude
    private List<SkillDTO> expectedskills = new ArrayList<>();
    @PodamExclude
    private CategoryDTO category;
    @PodamExclude
    private StatusDTO status;
    @PodamExclude
    private CustomerDTO customer;
    private String description;
    
    
    

    /**
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @generated
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @generated
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @generated
     */
    public String getRecommendedTime() {
        return recommendedTime;
    }

    /**
     * @generated
     */
    public void setRecommendedTime(String recommendedtime) {
        this.recommendedTime = recommendedtime;
    }

    /**
     * @generated
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @generated
     */
    public void setCreationDate(Date creationdate) {
        this.creationDate = creationdate;
    }

    /**
     * @generated
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @generated
     */
    public void setDueDate(Date duedate) {
        this.dueDate = duedate;
    }

    /**
     * @generated
     */
    public CategoryDTO getCategory() {
        return category;
    }

    /**
     * @generated
     */
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    /**
     * @generated
     */
    public CustomerDTO getCustomer() {
        return customer;
    }

    /**
     * @generated
     */
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    /**
     * @generated
     */
    public StatusDTO getStatus() {
        return status;
    }

    /**
     * @generated
     */
    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    /**
     * @generated
     */
    public List<SkillDTO> getExpectedskills() {
        return expectedskills;
    }

    /**
     * @generated
     */
    public void setExpectedskills(List<SkillDTO> expectedskills) {
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

    /**
     * @return the score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Integer score) {
        this.score = score;
    }   

    /**
     * @return the priceRequestLimit
     */
    public Date getPriceRequestLimit() {
        return priceRequestLimit;
    }

    /**
     * @param priceRequestLimit the priceRequestLimit to set
     */
    public void setPriceRequestLimit(Date priceRequestLimit) {
        this.priceRequestLimit = priceRequestLimit;
    }
}
