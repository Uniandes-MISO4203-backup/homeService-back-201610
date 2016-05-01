package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.ReviewDTO;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.entities.ReviewEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class ReviewConverter {

    private ReviewConverter() {
    }

    /**
     * Convierte una instancia de ReviewEntity a ReviewDTO
     * Convierte todos los atributos propios de ReviewEntity
     * @param entity Instancia de ReviewEntity a convertir
     * @return 
     */
    public static ReviewDTO basicEntity2DTO(ReviewEntity entity) {
        if (entity != null) {

            ReviewDTO dto = new ReviewDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setValue(entity.getValue());
            dto.setContractor(ContractorConverter.refEntity2DTO(entity.getContractor()));
            dto.setCustomer(CustomerConverter.refEntity2DTO(entity.getCustomer()));

            return dto;
        }
        return null;
    }

    /**
     * Convierte una instancia de ReviewDTO a ReviewEntity
     * Convierte todos los atributos propios de ReviewDTO
     * @param dto
     * @return 
     */
    public static ReviewEntity basicDTO2Entity(ReviewDTO dto) {
        if (dto != null) {

            ReviewEntity entity = new ReviewEntity();

            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setValue(dto.getValue());
            entity.setContractor(ContractorConverter.refDTO2Entity(dto.getContractor()));
            entity.setCustomer(CustomerConverter.refDTO2Entity(dto.getCustomer()));

            return entity;
        }
        return null;
    }

    /**
     * Convierte una colección de ReviewEntity a ReviewDTO
     * Por cada instancia de ReviewEntity, se ejecuta basicEntity2DTO y se guarda
     * el resultado en una nueva colección
     * @param entities Colección de instancias de ReviewEntity
     * @return Colección de instancias de ReviewDTO
     */
    public static List<ReviewDTO> listEntity2DTO(List<ReviewEntity> entities) {
        List<ReviewDTO> dtos = new ArrayList<ReviewDTO>();
        if (entities != null) {
            for (ReviewEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una coleccuón de ReviewDTO a ReviewEntity
     * Por cada instancia de ReviewDTO se ejecuta basicDTO2Entity y se guarda
     * el resultado en una nueva colección
     * @param dtos Colección de instancias de ReviewDTO
     * @return Colección de instancias de ReviewEntity
     */
    public static List<ReviewEntity> listDTO2Entity(List<ReviewDTO> dtos) {
        List<ReviewEntity> entities = new ArrayList<ReviewEntity>();
        if (dtos != null) {
            for (ReviewDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    /**
     * Convierte una instancia de ReviewDTO a ReviewEntity asignando un valor
     * al atributo Contractor de ReviewEntity. Se usa cuando se necesita convertir 
     * un ReviewDTO asignando el libro asociado
     * @param dto Instancia de ReviewDTO
     * @param parent Instancia de ContractorEntity
     * @return Instancia de ReviewEntity con ContractorEntity asociado
     */
    public static ReviewEntity childDTO2Entity(ReviewDTO dto, ContractorEntity parent) {
        ReviewEntity entity = basicDTO2Entity(dto);
        entity.setContractor(parent);
        return entity;
    }

    /**
     * Convierte una colección de instancias de ReviewDTO a ReviewEntity 
     * asignando el mismo padre para todos. Se usa cuando se necesita crear o 
     * actualizar varios ReviewEntity con el mismo Contractor
     * @param dtos Colección de instancias de ReviewDTO
     * @param parent Instancia de ContractorEntity
     * @return Colección de ReviewEntity con el atributo Contractor asignado
     */
    public static List<ReviewEntity> childListDTO2Entity(List<ReviewDTO> dtos, ContractorEntity parent) {
        List<ReviewEntity> entities = new ArrayList<ReviewEntity>();
        if (dtos != null) {
            for (ReviewDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
}
