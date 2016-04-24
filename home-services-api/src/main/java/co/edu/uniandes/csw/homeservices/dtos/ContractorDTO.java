package co.edu.uniandes.csw.homeservices.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@XmlRootElement
public class ContractorDTO {

    private Long id;
    private String name;
    private String lastName;
    private String document;
    private String picture;
    private String city;
    private String telefono;
    private String email;
    
    @PodamExclude
    private List<SkillDTO> skills = new ArrayList<>();
    
    private List<WorkExperienceDTO> workExperiences = new ArrayList<>();
    
    private List<EducationDTO> educations = new ArrayList<>();

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
    public String getLastName() {
        return lastName;
    }

    /**
     * @generated
     */
    public void setLastName(String lastname) {
        this.lastName = lastname;
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
    public List<SkillDTO> getSkills() {
        return skills;
    }

    /**
     * @generated
     */
    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }

    /**
     * @generated
     */
    public List<WorkExperienceDTO> getWorkExperiences() {
        return workExperiences;
    }

    /**
     * @generated
     */
    public void setWorkExperiences(List<WorkExperienceDTO> workexperiences) {
        this.workExperiences = workexperiences;
    }

    public List<EducationDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationDTO> educations) {
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
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
