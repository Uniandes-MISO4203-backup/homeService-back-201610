package co.edu.uniandes.csw.homeservices.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@XmlRootElement
public class CustomerDTO {

    private Long id;
    private String name;
    private String lastName;
    private String document;
    private String picture;
    private String address;
    @PodamExclude
    private List<ServiceRequestDTO> serviceRequests = new ArrayList<>();

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    

    /**
     * @generated
     */
    public List<ServiceRequestDTO> getServiceRequests() {
        return serviceRequests;
    }

    /**
     * @generated
     */
    public void setServiceRequests(List<ServiceRequestDTO> servicerequests) {
        this.serviceRequests = servicerequests;
    }

}
