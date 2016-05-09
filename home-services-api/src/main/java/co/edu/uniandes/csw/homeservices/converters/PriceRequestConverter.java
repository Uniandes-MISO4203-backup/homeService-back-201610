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
        if (entity != null) {
            PriceRequestDTO dto = new PriceRequestDTO();
            dto.setId(entity.getId());
            dto.setStatus(entity.getStatus());
            dto.setPrice(entity.getPrice());
            dto.setDescription(entity.getDescription());
            return dto;
        } else {
            return null;
        }
    }
     
     public static PriceRequestEntity refDTO2Entity(PriceRequestDTO dto){
        PriceRequestEntity entity = new PriceRequestEntity();
        entity.setId(dto.getId());
        entity.setStatus(dto.getStatus());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        return entity;
    }
    
    public static PriceRequestDTO basicEntity2DTO(PriceRequestEntity entity){
        if (entity != null) {
            PriceRequestDTO dto = new PriceRequestDTO();
            dto.setId(entity.getId());
            dto.setStatus(entity.getStatus());        
            dto.setPrice(entity.getPrice());
            dto.setDescription(entity.getDescription());
            dto.setServiceRequestDTO(ServiceRequestConverter.fullEntity2DTO(entity.getServiceRequest()));
            dto.setContractorDTO(ContractorConverter.refEntity2DTO(entity.getContractor()));
            return dto;
        } else {
            return null;
        }
    }
    
    public static PriceRequestEntity basicDTO2Entity(PriceRequestDTO dto){
        if (dto != null) {
            PriceRequestEntity entity = new PriceRequestEntity();
            entity.setId(dto.getId());
            entity.setStatus(dto.getStatus());
            entity.setPrice(dto.getPrice());
            entity.setDescription(dto.getDescription());
            entity.setServiceRequest(ServiceRequestConverter.refDTO2Entity(dto.getServiceRequestDTO()));
            entity.setContractor(ContractorConverter.refDTO2Entity(dto.getContractorDTO()));
            return entity;
        } else {
            return null;
        }
    }
    
    public static List<PriceRequestDTO> listEntity2DTO(List<PriceRequestEntity> entities){
        List<PriceRequestDTO> list = new ArrayList<PriceRequestDTO>();
        if (entities != null) {
            for (PriceRequestEntity entity : entities) {
            list.add(basicEntity2DTO(entity));
            }
        }
        return list;
    }
    
        /**
     * Convierte una instancia de ServiceRequestDTO a ServiceRequestEntity.
     * Incluye todos los atributos de ServiceRequestEntity.
     *
     * @param dto Instancia de ServiceRequestDTO a convertir
     * @return Instancia de ServiceRequestEntity con los datos recibidos por parámetro
     * @generated
     */
    public static PriceRequestEntity fullDTO2Entity(PriceRequestDTO dto) {
        if (dto != null) {
            return basicDTO2Entity(dto);
        } else {
            return null;
        }
    }
    
    /**
     * Convierte instancias de ServiceRequestEntity a ServiceRequestDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de ServiceRequestEntity a convertir
     * @return Instancia de ServiceRequestDTO con los datos recibidos por parámetro
     * @generated
     */
    public static PriceRequestDTO fullEntity2DTO(PriceRequestEntity entity) {
        if (entity != null) {
            return basicEntity2DTO(entity);
        } else {
            return null;
        }
    }
 
    /**
     * Convierte una colección de instancias de CategoryDTO a instancias de
     * CategoryEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de CategoryDTO a convertir
     * @return Collección de instancias de CategoryEntity
     * @generated
     */
    public static List<PriceRequestEntity> listDTO2Entity(List<PriceRequestDTO> dtos) {
        List<PriceRequestEntity> entities = new ArrayList<PriceRequestEntity>();
        if (dtos != null) {
            for (PriceRequestDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
}
