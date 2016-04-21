package co.edu.uniandes.csw.homeservices.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * @generated
 */
@Entity
public class SkillEntity extends BaseEntity implements Serializable {

    @JoinTable (name = "CONTRACTORENTITY_SKILLENTITY", 
            inverseJoinColumns = { @JoinColumn(name = "CONTRACTORENTITY_ID") },
            joinColumns = { @JoinColumn(name = "SKILLS_ID")}
    )
    @ManyToMany(cascade=CascadeType.ALL)
    private List<ContractorEntity> contractors;
    
    private String description;

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

    public List<ContractorEntity> getContractors() {
        return contractors;
    }

    public void setContractors(List<ContractorEntity> contractors) {
        this.contractors = contractors;
    }
    
    
}
