/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.dtos;

import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author af.esguerra10
 */
public class ReviewDTO {

    private Long id;

    private String name;

    private String source;

    private String description;

    @PodamExclude
    private ContractorDTO contractor;
    
    @PodamExclude
    private CustomerDTO customer;
    
    private int value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ContractorDTO getContractor() {
        return contractor;
    }

    public void setContractor(ContractorDTO contractor) {
        this.contractor = contractor;
    }
    
    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
