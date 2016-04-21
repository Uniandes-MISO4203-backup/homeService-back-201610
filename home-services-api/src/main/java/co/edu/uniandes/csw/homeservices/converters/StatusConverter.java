package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.StatusDTO;
import co.edu.uniandes.csw.homeservices.entities.StatusEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class StatusConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private StatusConverter() {
    }

    /**
     * Realiza la conversión de StatusEntity a StatusDTO.
     * Se invoca cuando otra entidad tiene una referencia a StatusEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de StatusEntity a convertir
     * @return instancia de StatusDTO con los datos recibidos por parámetro
     * @generated
     */
    public static StatusDTO refEntity2DTO(StatusEntity entity) {
        if (entity != null) {
            StatusDTO dto = new StatusDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de StatusDTO a StatusEntity Se invoca cuando otro DTO
     * tiene una referencia a StatusDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de StatusDTO a convertir
     * @return instancia de StatusEntity con los datos recibidos por parámetro
     * @generated
     */
    public static StatusEntity refDTO2Entity(StatusDTO dto) {
        if (dto != null) {
            StatusEntity entity = new StatusEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de StatusEntity a StatusDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de StatusEntity a convertir
     * @return Instancia de StatusDTO con los datos recibidos por parámetro
     * @generated
     */
    private static StatusDTO basicEntity2DTO(StatusEntity entity) {
        if (entity != null) {
            StatusDTO dto = new StatusDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de StatusDTO a StatusEntity Se invoca cuando se
     * necesita convertir una instancia de StatusDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de StatusDTO a convertir
     * @return Instancia de StatusEntity creada a partir de los datos de dto
     * @generated
     */
    private static StatusEntity basicDTO2Entity(StatusDTO dto) {
        if (dto != null) {
            StatusEntity entity = new StatusEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de StatusEntity a StatusDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de StatusEntity a convertir
     * @return Instancia de StatusDTO con los datos recibidos por parámetro
     * @generated
     */
    public static StatusDTO fullEntity2DTO(StatusEntity entity) {
        if (entity != null) {
            StatusDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de StatusDTO a StatusEntity.
     * Incluye todos los atributos de StatusEntity.
     *
     * @param dto Instancia de StatusDTO a convertir
     * @return Instancia de StatusEntity con los datos recibidos por parámetro
     * @generated
     */
    public static StatusEntity fullDTO2Entity(StatusDTO dto) {
        if (dto != null) {
            StatusEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de StatusEntity a StatusDTO. Para cada
     * instancia de StatusEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo StatusDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de StatusDTO
     * @generated
     */
    public static List<StatusDTO> listEntity2DTO(List<StatusEntity> entities) {
        List<StatusDTO> dtos = new ArrayList<StatusDTO>();
        if (entities != null) {
            for (StatusEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de StatusDTO a instancias de
     * StatusEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de StatusDTO a convertir
     * @return Collección de instancias de StatusEntity
     * @generated
     */
    public static List<StatusEntity> listDTO2Entity(List<StatusDTO> dtos) {
        List<StatusEntity> entities = new ArrayList<StatusEntity>();
        if (dtos != null) {
            for (StatusDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
