package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.SkillDTO;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class SkillConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private SkillConverter() {
    }

    /**
     * Realiza la conversión de SkillEntity a SkillDTO.
     * Se invoca cuando otra entidad tiene una referencia a SkillEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de SkillEntity a convertir
     * @return instancia de SkillDTO con los datos recibidos por parámetro
     * @generated
     */
    public static SkillDTO refEntity2DTO(SkillEntity entity) {
        if (entity != null) {
            SkillDTO dto = new SkillDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de SkillDTO a SkillEntity Se invoca cuando otro DTO
     * tiene una referencia a SkillDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de SkillDTO a convertir
     * @return instancia de SkillEntity con los datos recibidos por parámetro
     * @generated
     */
    public static SkillEntity refDTO2Entity(SkillDTO dto) {
        if (dto != null) {
            SkillEntity entity = new SkillEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de SkillEntity a SkillDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de SkillEntity a convertir
     * @return Instancia de SkillDTO con los datos recibidos por parámetro
     * @generated
     */
    private static SkillDTO basicEntity2DTO(SkillEntity entity) {
        if (entity != null) {
            SkillDTO dto = new SkillDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de SkillDTO a SkillEntity Se invoca cuando se
     * necesita convertir una instancia de SkillDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de SkillDTO a convertir
     * @return Instancia de SkillEntity creada a partir de los datos de dto
     * @generated
     */
    private static SkillEntity basicDTO2Entity(SkillDTO dto) {
        if (dto != null) {
            SkillEntity entity = new SkillEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de SkillEntity a SkillDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de SkillEntity a convertir
     * @return Instancia de SkillDTO con los datos recibidos por parámetro
     * @generated
     */
    public static SkillDTO fullEntity2DTO(SkillEntity entity) {
        if (entity != null) {
            SkillDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de SkillDTO a SkillEntity.
     * Incluye todos los atributos de SkillEntity.
     *
     * @param dto Instancia de SkillDTO a convertir
     * @return Instancia de SkillEntity con los datos recibidos por parámetro
     * @generated
     */
    public static SkillEntity fullDTO2Entity(SkillDTO dto) {
        if (dto != null) {
            SkillEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de SkillEntity a SkillDTO. Para cada
     * instancia de SkillEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo SkillDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de SkillDTO
     * @generated
     */
    public static List<SkillDTO> listEntity2DTO(List<SkillEntity> entities) {
        List<SkillDTO> dtos = new ArrayList<SkillDTO>();
        if (entities != null) {
            for (SkillEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de SkillDTO a instancias de
     * SkillEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de SkillDTO a convertir
     * @return Collección de instancias de SkillEntity
     * @generated
     */
    public static List<SkillEntity> listDTO2Entity(List<SkillDTO> dtos) {
        List<SkillEntity> entities = new ArrayList<SkillEntity>();
        if (dtos != null) {
            for (SkillDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
