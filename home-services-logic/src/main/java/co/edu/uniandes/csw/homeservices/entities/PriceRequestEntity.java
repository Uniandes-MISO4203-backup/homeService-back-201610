/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad de negocio de solicitud de cotización. 
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "PriceRequest.getByCustomer", query = "SELECT pr FROM PriceRequestEntity pr JOIN pr.serviceRequest sr JOIN sr.customer c1 WHERE c1.id = :customerId")
})   
public class PriceRequestEntity extends BaseEntity implements Serializable{
    
    /* 
        Estados de la solicitud de cotización
        PENDIENTE (Se listan en la pantalla del contratista y el cliente)
        RECHAZADA (Se dejan de visualizar en la pantalla del contratista y si la solicitud de servicio no está cerrada se visualizan en la pantalla del cliente)
        ACEPTADA(Se listan en la pantalla del cliente)
    */

    private String status;
    
    @PodamExclude
    @ManyToOne
    private ServiceRequestEntity serviceRequest;
    
    @PodamExclude
    @ManyToOne
    private ContractorEntity contractor;
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ServiceRequestEntity getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequestEntity serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public ContractorEntity getContractor() {
        return contractor;
    }

    public void setContractor(ContractorEntity contractor) {
        this.contractor = contractor;
    }
    
    
}