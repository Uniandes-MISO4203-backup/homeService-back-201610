package co.edu.uniandes.csw.homeservices.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;

/**
 * @generated
 */
@Entity
public class ContractorEntity extends BaseEntity implements Serializable {

    private String lastName;

    private String document;

    private String picture;
    
    private String telefono;

    @PodamExclude
    @ManyToMany
    private List<SkillEntity> skills = new ArrayList<>();

    
    @OneToMany(mappedBy = "contractor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkExperienceEntity> workExperiences = new ArrayList<>();

    /**
     * @generated
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * @generated
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * @generated
     */
    public String getDocument(){
        return document;
    }

    /**
     * @generated
     */
    public void setDocument(String document){
        this.document = document;
    }

    /**
     * @generated
     */
    public String getPicture(){
        return picture;
    }

    /**
     * @generated
     */
    public void setPicture(String picture){
        this.picture = picture;
    }

    /**
     * @generated
     */
    public List<SkillEntity> getSkills() {
        return skills;
    }

    /**
     * @generated
     */
    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }

    /**
     * @generated
     */
    public List<WorkExperienceEntity> getWorkExperiences() {
        return workExperiences;
    }

    /**
     * @generated
     */
    public void setWorkExperiences(List<WorkExperienceEntity> workexperiences) {
        this.workExperiences = workexperiences;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}
