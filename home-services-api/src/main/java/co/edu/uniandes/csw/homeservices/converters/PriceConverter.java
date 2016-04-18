/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.PriceDTO;
import co.edu.uniandes.csw.homeservices.entities.PriceEntity;

/**
 *
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
public class PriceConverter {
    
    public static PriceDTO refEntity2DTO(PriceEntity entity){
        PriceDTO dto = new PriceDTO();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        return dto;
    }
    
    public static PriceEntity refDTO2Entity(PriceDTO dto){
        PriceEntity entity = new PriceEntity();
        entity.setId(dto.getId());
        return entity;
    }
    
    public static PriceDTO basicEntity2DTO(PriceEntity entity){
        PriceDTO dto = new PriceDTO();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setPriceRequestDTO(PriceRequestConverter.refEntity2DTO(entity.getPriceRequest()));
        return dto;
    }
    
    
    public static PriceEntity basicDTO2Entity(PriceDTO dto){
        PriceEntity entity = new PriceEntity();
        entity.setId(dto.getId());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setPriceRequest(PriceRequestConverter.refDTO2Entity(dto.getPriceRequestDTO()));
        return entity;
    }
    
}
