package co.edu.uniandes.csw.homeservices.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;

/**
 * @generated
 */
@Entity
public class WorkExperienceEntity extends BaseEntity implements Serializable {

    private String description;
    /*
        How many hour took this job
    */
    private Integer hours;

    @PodamExclude
    @ManyToOne
    private ContractorEntity contractor;

    /**
     * @generated
     */
    public String getDescription(){
        return description;
    }

    /**
     * @generated
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * @generated
     */
    public ContractorEntity getContractor() {
        return contractor;
    }

    /**
     * @generated
     */
    public void setContractor(ContractorEntity contractor) {
        this.contractor = contractor;
    }

    /**
     * @return the hours
     */
    public Integer getHours() {
        return hours;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
