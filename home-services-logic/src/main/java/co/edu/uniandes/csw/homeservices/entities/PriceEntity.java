/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad de negocio de cotización
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "PriceEntity.getByCustomer", query = "SELECT p FROM PriceEntity p JOIN PriceRequestEntity pr JOIN pr.serviceRequest sr JOIN sr.customer c1 WHERE c1.id = :customerId")
})
public class PriceEntity extends BaseEntity implements Serializable{
    
    private Integer price;
    private String description;
    
    @PodamExclude
    @OneToOne
    private PriceRequestEntity priceRequest;

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

    public PriceRequestEntity getPriceRequest() {
        return priceRequest;
    }

    public void setPriceRequest(PriceRequestEntity priceRequest) {
        this.priceRequest = priceRequest;
    }
    
}
