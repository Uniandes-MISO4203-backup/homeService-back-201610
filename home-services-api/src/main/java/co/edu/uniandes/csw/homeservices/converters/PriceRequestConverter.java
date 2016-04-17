/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.PriceRequestDTO;
import co.edu.uniandes.csw.homeservices.entities.PriceRequestEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
public class PriceRequestConverter {
    
     public static PriceRequestDTO refEntity2DTO(PriceRequestEntity entity){
        PriceRequestDTO dto = new PriceRequestDTO();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        return dto;
    }
     
     public static PriceRequestEntity refDTO2Entity(PriceRequestDTO dto){
        PriceRequestEntity entity = new PriceRequestEntity();
        entity.setId(dto.getId());
        entity.setStatus(dto.getStatus());
        return entity;
    }
    
    public static PriceRequestDTO basicEntity2DTO(PriceRequestEntity entity){
        PriceRequestDTO dto = new PriceRequestDTO();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setServiceRequestDTO(ServiceRequestConverter.refEntity2DTO(entity.getServiceRequest()));
        dto.setContractorDTO(ContractorConverter.refEntity2DTO(entity.getContractor()));
        return dto;
    }
    
    public static PriceRequestEntity basicDTO2Entity(PriceRequestDTO dto){
        PriceRequestEntity entity = new PriceRequestEntity();
        entity.setId(dto.getId());
        entity.setStatus(dto.getStatus());
        entity.setServiceRequest(ServiceRequestConverter.refDTO2Entity(dto.getServiceRequestDTO()));
        entity.setContractor(ContractorConverter.refDTO2Entity(dto.getContractorDTO()));
        return entity;
    }
    
    public static List<PriceRequestDTO> listEntity2DTO(List<PriceRequestEntity> entities){
        List<PriceRequestDTO> list = new ArrayList<PriceRequestDTO>();
        for (PriceRequestEntity entity : entities) {
            list.add(basicEntity2DTO(entity));
        }
        return list;
    }
}
