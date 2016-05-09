package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.CategoryDTO;
import co.edu.uniandes.csw.homeservices.entities.CategoryEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class CategoryConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private CategoryConverter() {
    }

    /**
     * Realiza la conversión de CategoryEntity a CategoryDTO.
     * Se invoca cuando otra entidad tiene una referencia a CategoryEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de CategoryEntity a convertir
     * @return instancia de CategoryDTO con los datos recibidos por parámetro
     * @generated
     */
    public static CategoryDTO refEntity2DTO(CategoryEntity entity) {
        if (entity != null) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de CategoryDTO a CategoryEntity Se invoca cuando otro DTO
     * tiene una referencia a CategoryDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de CategoryDTO a convertir
     * @return instancia de CategoryEntity con los datos recibidos por parámetro
     * @generated
     */
    public static CategoryEntity refDTO2Entity(CategoryDTO dto) {
        if (dto != null) {
            CategoryEntity entity = new CategoryEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CategoryEntity a CategoryDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de CategoryEntity a convertir
     * @return Instancia de CategoryDTO con los datos recibidos por parámetro
     * @generated
     */
    private static CategoryDTO basicEntity2DTO(CategoryEntity entity) {
        if (entity != null) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CategoryDTO a CategoryEntity Se invoca cuando se
     * necesita convertir una instancia de CategoryDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de CategoryDTO a convertir
     * @return Instancia de CategoryEntity creada a partir de los datos de dto
     * @generated
     */
    private static CategoryEntity basicDTO2Entity(CategoryDTO dto) {
        if (dto != null) {
            CategoryEntity entity = new CategoryEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de CategoryEntity a CategoryDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de CategoryEntity a convertir
     * @return Instancia de CategoryDTO con los datos recibidos por parámetro
     * @generated
     */
    public static CategoryDTO fullEntity2DTO(CategoryEntity entity) {
        if (entity != null) {
            return basicEntity2DTO(entity);
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CategoryDTO a CategoryEntity.
     * Incluye todos los atributos de CategoryEntity.
     *
     * @param dto Instancia de CategoryDTO a convertir
     * @return Instancia de CategoryEntity con los datos recibidos por parámetro
     * @generated
     */
    public static CategoryEntity fullDTO2Entity(CategoryDTO dto) {
        if (dto != null) {
            return basicDTO2Entity(dto);
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de CategoryEntity a CategoryDTO. Para cada
     * instancia de CategoryEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo CategoryDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de CategoryDTO
     * @generated
     */
    public static List<CategoryDTO> listEntity2DTO(List<CategoryEntity> entities) {
        List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
        if (entities != null) {
            for (CategoryEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de CategoryDTO a instancias de
     * CategoryEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de CategoryDTO a convertir
     * @return Collección de instancias de CategoryEntity
     * @generated
     */
    public static List<CategoryEntity> listDTO2Entity(List<CategoryDTO> dtos) {
        List<CategoryEntity> entities = new ArrayList<CategoryEntity>();
        if (dtos != null) {
            for (CategoryDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
