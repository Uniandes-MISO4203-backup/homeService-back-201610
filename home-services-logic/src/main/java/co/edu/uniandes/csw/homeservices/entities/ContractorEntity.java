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

    private String city;

    private String telefono;

    private String profileDescription;
    
    private String email;
    
    @PodamExclude
    @OneToMany(mappedBy = "contractor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews;
        

    @PodamExclude
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "contractors")
    private List<SkillEntity> skills = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "contractor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkExperienceEntity> workExperiences = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "contractor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EducationEntity> educations = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "contractor")
    private List<ChatNameEntity> chatName= new ArrayList<>();

    /**
     * @OneToMany(mappedBy = "contractor", cascade = CascadeType.ALL,
     * orphanRemoval = true)
     * @generated
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @generated
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @generated
     */
    public String getDocument() {
        return document;
    }

    /**
     * @generated
     */
    public void setDocument(String document) {
        this.document = document;
    }

    /**
     * @generated
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @generated
     */
    public void setPicture(String picture) {
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

    public List<EducationEntity> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationEntity> educations) {
        this.educations = educations;
    }

    /**
     * @generated
     */
    public String getCity() {
        return city;
    }

    /**
     * @generated
     */
    public void setCity(String city) {
        this.city = city;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }
    
        public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the chatName
     */
    public List<ChatNameEntity> getChatName() {
        return chatName;
    }

    /**
     * @param chatName the chatName to set
     */
    public void setChatName(List<ChatNameEntity> chatName) {
        this.chatName = chatName;
    }
    
    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

}