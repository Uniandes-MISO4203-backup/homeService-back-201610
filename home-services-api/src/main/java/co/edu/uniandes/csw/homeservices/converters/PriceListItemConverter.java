/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.PriceListItemDTO;
import co.edu.uniandes.csw.homeservices.entities.PriceRequestEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author j.garcia100
 */
public abstract class PriceListItemConverter {
    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     */
    private PriceListItemConverter() {
    }
    
    /**
     * Realiza la conversión de PriceRequestEntity a PriceListItemDTO.
     * Se invoca cuando otra entidad tiene una referencia a PriceRequestEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de PriceRequestEntity a convertir
     * @return instancia de PriceListItemDTO con los datos recibidos por parámetro
     */
    public static PriceListItemDTO refEntity2DTO(PriceRequestEntity entity) {
        if (entity != null) {
            PriceListItemDTO dto = new PriceListItemDTO();
            dto.setId(entity.getId());
            dto.setPrice(entity.getPrice());
            dto.setDescription(entity.getDescription());
            dto.setPriceRequestStatus(entity.getStatus());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de PriceRequestEntity a PriceListItemDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de PriceRequestEntity a convertir
     * @return Instancia de PriceListItemDTO con los datos recibidos por parámetro
     */
    public static PriceListItemDTO fullEntity2DTO(PriceRequestEntity entity) {
        if (entity != null) {
            PriceListItemDTO dto = refEntity2DTO(entity);
            dto.setContractor(ContractorConverter.refEntity2DTO(entity.getContractor()));
            dto.setServiceRequest(ServiceRequestConverter.refEntity2DTO(entity.getServiceRequest()));
            return dto;
        } else {
            return null;
        }
    }
    
     /**
     * Convierte una colección de instancias de ServiceRequestEntity a ServiceRequestDTO. Para cada
     * instancia de ServiceRequestEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo ServiceRequestDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de ServiceRequestDTO
     */
    public static List<PriceListItemDTO> listEntity2DTO(List<PriceRequestEntity> entities) {
        List<PriceListItemDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (PriceRequestEntity entity : entities) {
                dtos.add(fullEntity2DTO(entity));
            }
        }
        return dtos;
    }
}
