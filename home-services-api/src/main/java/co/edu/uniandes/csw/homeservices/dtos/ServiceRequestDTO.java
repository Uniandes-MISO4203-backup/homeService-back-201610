package co.edu.uniandes.csw.homeservices.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import co.edu.uniandes.csw.auth.model.DateAdapter;
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
    @XmlJavaTypeAdapter(DateAdapter.class)
    @PodamStrategyValue(DateStrategy.class)
    private Date creationDate;
    @XmlJavaTypeAdapter(DateAdapter.class)
    @PodamStrategyValue(DateStrategy.class)
    private Date dueDate;
    private String statusService;
    @PodamExclude
    private CustomerDTO customer;
    @PodamExclude
    private List<SkillDTO> expectedskills = new ArrayList<>();
    @PodamExclude
    private StatusDTO status;
    @PodamExclude
    private CategoryDTO category;

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
    public String getStatusService() {
        return statusService;
    }

    /**
     * @generated
     */
    public void setStatusService(String statusservice) {
        this.statusService = statusservice;
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
    public List<SkillDTO> getExpectedskills() {
        return expectedskills;
    }

    /**
     * @generated
     */
    public void setExpectedskills(List<SkillDTO> expectedskills) {
        this.expectedskills = expectedskills;
    }

}
