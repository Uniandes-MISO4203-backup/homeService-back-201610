package co.edu.uniandes.csw.homeservices.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

/**
 * @generated
 */
@Entity
public class CustomerEntity extends BaseEntity implements Serializable {

    private String lastName;

    private String document;

    private String picture;
    
    private String address;

    @PodamExclude
    @OneToMany(mappedBy = "customer")
    private List<ServiceRequestEntity> serviceRequests = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "customer")
    private List<ChatNameEntity> chatName= new ArrayList<>();
    

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
    public List<ServiceRequestEntity> getServiceRequests() {
        return serviceRequests;
    }

    /**
     * @generated
     */
    public void setServiceRequests(List<ServiceRequestEntity> servicerequests) {
        this.serviceRequests = servicerequests;
    }
    
     public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    
}
