/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.dtos;

/**
 *
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
public class PriceRequestDTO {
    
    private Long id;
    private String status;
    private Integer price;
    private String description;
    private ServiceRequestDTO serviceRequestDTO;
    private ContractorDTO contractorDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ServiceRequestDTO getServiceRequestDTO() {
        return serviceRequestDTO;
    }

    public void setServiceRequestDTO(ServiceRequestDTO serviceRequestDTO) {
        this.serviceRequestDTO = serviceRequestDTO;
    }

    public ContractorDTO getContractorDTO() {
        return contractorDTO;
    }

    public void setContractorDTO(ContractorDTO contractorDTO) {
        this.contractorDTO = contractorDTO;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
}
