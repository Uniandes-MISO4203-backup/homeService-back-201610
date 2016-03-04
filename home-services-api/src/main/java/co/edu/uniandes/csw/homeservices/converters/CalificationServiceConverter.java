/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.CalificationServiceDTO;
import co.edu.uniandes.csw.homeservices.entities.CalificationServiceEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author l.badillo10
 */
public abstract class CalificationServiceConverter {
    
    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private CalificationServiceConverter() {
    }

    /**
     * Realiza la conversión de CalificationServiceEntity a CalificationServiceDTO.
     * Se invoca cuando otra entidad tiene una referencia a CalificationServiceEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de CalificationServiceEntity a convertir
     * @return instancia de CalificationServiceDTO con los datos recibidos por parámetro
     * @generated
     */
    public static CalificationServiceDTO refEntity2DTO(CalificationServiceEntity entity) {
        if (entity != null) {
            CalificationServiceDTO dto = new CalificationServiceDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de CalificationServiceDTO a CalificationServiceEntity Se invoca cuando otro DTO
     * tiene una referencia a CalificationServiceDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de CalificationServiceDTO a convertir
     * @return instancia de CalificationServiceEntity con los datos recibidos por parámetro
     * @generated
     */
    public static CalificationServiceEntity refDTO2Entity(CalificationServiceDTO dto) {
        if (dto != null) {
            CalificationServiceEntity entity = new CalificationServiceEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CalificationServiceEntity a CalificationServiceDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de CalificationServiceEntity a convertir
     * @return Instancia de CalificationServiceDTO con los datos recibidos por parámetro
     * @generated
     */
    private static CalificationServiceDTO basicEntity2DTO(CalificationServiceEntity entity) {
        if (entity != null) {
            CalificationServiceDTO dto = new CalificationServiceDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CalificationServiceDTO a CalificationServiceEntity Se invoca cuando se
     * necesita convertir una instancia de CalificationServiceDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de CalificationServiceDTO a convertir
     * @return Instancia de CalificationServiceEntity creada a partir de los datos de dto
     * @generated
     */
    private static CalificationServiceEntity basicDTO2Entity(CalificationServiceDTO dto) {
        if (dto != null) {
            CalificationServiceEntity entity = new CalificationServiceEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de CalificationServiceEntity a CalificationServiceDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de CalificationServiceEntity a convertir
     * @return Instancia de CalificationServiceDTO con los datos recibidos por parámetro
     * @generated
     */
    public static CalificationServiceDTO fullEntity2DTO(CalificationServiceEntity entity) {
        if (entity != null) {
            CalificationServiceDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CalificationServiceDTO a CalificationServiceEntity.
     * Incluye todos los atributos de CalificationServiceEntity.
     *
     * @param dto Instancia de CalificationServiceDTO a convertir
     * @return Instancia de CalificationServiceEntity con los datos recibidos por parámetro
     * @generated
     */
    public static CalificationServiceEntity fullDTO2Entity(CalificationServiceDTO dto) {
        if (dto != null) {
            CalificationServiceEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de CalificationServiceEntity a CalificationServiceDTO. Para cada
     * instancia de CalificationServiceEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo CalificationServiceDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de CalificationServiceDTO
     * @generated
     */
    public static List<CalificationServiceDTO> listEntity2DTO(List<CalificationServiceEntity> entities) {
        List<CalificationServiceDTO> dtos = new ArrayList<CalificationServiceDTO>();
        if (entities != null) {
            for (CalificationServiceEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de CalificationServiceDTO a instancias de
     * CalificationServiceEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de CalificationServiceDTO a convertir
     * @return Collección de instancias de CalificationServiceEntity
     * @generated
     */
    public static List<CalificationServiceEntity> listDTO2Entity(List<CalificationServiceDTO> dtos) {
        List<CalificationServiceEntity> entities = new ArrayList<CalificationServiceEntity>();
        if (dtos != null) {
            for (CalificationServiceDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
}
