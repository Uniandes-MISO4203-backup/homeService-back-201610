/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.dtos;


import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author j.garcia100
 */
@XmlRootElement
public class PriceListItemDTO {
    private Long id;
    private Integer price;
    private String description;
    private String priceRequestStatus;
   
   
    @PodamExclude
    private ContractorDTO contractor;

    @PodamExclude
    private ServiceRequestDTO serviceRequest;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Integer price) {
        this.price = price;
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
     * @return the priceRequestStatus
     */
    public String getPriceRequestStatus() {
        return priceRequestStatus;
    }

    /**
     * @param priceRequestStatus the priceRequestStatus to set
     */
    public void setPriceRequestStatus(String priceRequestStatus) {
        this.priceRequestStatus = priceRequestStatus;
    }

    /**
     * @return the contractor
     */
    public ContractorDTO getContractor() {
        return contractor;
    }

    /**
     * @param contractor the contractor to set
     */
    public void setContractor(ContractorDTO contractor) {
        this.contractor = contractor;
    }

    /**
     * @return the serviceRequest
     */
    public ServiceRequestDTO getServiceRequest() {
        return serviceRequest;
    }

    /**
     * @param serviceRequest the serviceRequest to set
     */
    public void setServiceRequest(ServiceRequestDTO serviceRequest) {
        this.serviceRequest = serviceRequest;
    }
}
