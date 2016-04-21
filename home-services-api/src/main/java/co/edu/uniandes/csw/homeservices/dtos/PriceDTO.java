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
public class PriceDTO {
    private Long id;
    private Integer price;
    private String description;
    private PriceRequestDTO priceRequestDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PriceRequestDTO getPriceRequestDTO() {
        return priceRequestDTO;
    }

    public void setPriceRequestDTO(PriceRequestDTO priceRequestDTO) {
        this.priceRequestDTO = priceRequestDTO;
    }
    
    
}
