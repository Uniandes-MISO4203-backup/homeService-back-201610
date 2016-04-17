package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.ServiceRequestDTO;
import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class ServiceRequestConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private ServiceRequestConverter() {
    }

    /**
     * Realiza la conversión de ServiceRequestEntity a ServiceRequestDTO.
     * Se invoca cuando otra entidad tiene una referencia a ServiceRequestEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de ServiceRequestEntity a convertir
     * @return instancia de ServiceRequestDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ServiceRequestDTO refEntity2DTO(ServiceRequestEntity entity) {
        if (entity != null) {
            ServiceRequestDTO dto = new ServiceRequestDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setRecommendedTime(entity.getRecommendedTime());
            dto.setCreationDate(entity.getCreationDate());
            dto.setDueDate(entity.getDueDate());
            dto.setDescription(entity.getDescription());
            dto.setPriceRequestLimit(entity.getPriceRequestLimit());
           

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de ServiceRequestDTO a ServiceRequestEntity Se invoca cuando otro DTO
     * tiene una referencia a ServiceRequestDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de ServiceRequestDTO a convertir
     * @return instancia de ServiceRequestEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ServiceRequestEntity refDTO2Entity(ServiceRequestDTO dto) {
        if (dto != null) {
            ServiceRequestEntity entity = new ServiceRequestEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ServiceRequestEntity a ServiceRequestDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de ServiceRequestEntity a convertir
     * @return Instancia de ServiceRequestDTO con los datos recibidos por parámetro
     * @generated
     */
    private static ServiceRequestDTO basicEntity2DTO(ServiceRequestEntity entity) {
        if (entity != null) {
            ServiceRequestDTO dto = new ServiceRequestDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setRecommendedTime(entity.getRecommendedTime());
            dto.setCreationDate(entity.getCreationDate());
            dto.setDueDate(entity.getDueDate());
            dto.setCategory(CategoryConverter.refEntity2DTO(entity.getCategory()));
            dto.setCustomer(CustomerConverter.refEntity2DTO(entity.getCustomer()));
            dto.setStatus(StatusConverter.refEntity2DTO(entity.getStatus()));
            dto.setDescription(entity.getDescription());
            dto.setScore(entity.getScore());
            dto.setPriceRequestLimit(entity.getPriceRequestLimit());
           
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ServiceRequestDTO a ServiceRequestEntity Se invoca cuando se
     * necesita convertir una instancia de ServiceRequestDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de ServiceRequestDTO a convertir
     * @return Instancia de ServiceRequestEntity creada a partir de los datos de dto
     * @generated
     */
    private static ServiceRequestEntity basicDTO2Entity(ServiceRequestDTO dto) {
        if (dto != null) {
            ServiceRequestEntity entity = new ServiceRequestEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setPrice(dto.getPrice());
            entity.setRecommendedTime(dto.getRecommendedTime());
            entity.setCreationDate(dto.getCreationDate());
            entity.setDueDate(dto.getDueDate());
            entity.setCategory(CategoryConverter.refDTO2Entity(dto.getCategory()));
            entity.setCustomer(CustomerConverter.refDTO2Entity(dto.getCustomer()));
            entity.setStatus(StatusConverter.refDTO2Entity(dto.getStatus()));
            entity.setDescription(dto.getDescription());
            entity.setScore(dto.getScore());
            dto.setPriceRequestLimit(entity.getPriceRequestLimit());
          
            return entity;
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
    public static ServiceRequestDTO fullEntity2DTO(ServiceRequestEntity entity) {
        if (entity != null) {
            ServiceRequestDTO dto = basicEntity2DTO(entity);
            dto.setExpectedskills(SkillConverter.listEntity2DTO(entity.getExpectedskills()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ServiceRequestDTO a ServiceRequestEntity.
     * Incluye todos los atributos de ServiceRequestEntity.
     *
     * @param dto Instancia de ServiceRequestDTO a convertir
     * @return Instancia de ServiceRequestEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ServiceRequestEntity fullDTO2Entity(ServiceRequestDTO dto) {
        if (dto != null) {
            ServiceRequestEntity entity = basicDTO2Entity(dto);
            entity.setExpectedskills(SkillConverter.listDTO2Entity(dto.getExpectedskills()));
            return entity;
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
     * @generated
     */
    public static List<ServiceRequestDTO> listEntity2DTO(List<ServiceRequestEntity> entities) {
        List<ServiceRequestDTO> dtos = new ArrayList<ServiceRequestDTO>();
        if (entities != null) {
            for (ServiceRequestEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de ServiceRequestDTO a instancias de
     * ServiceRequestEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de ServiceRequestDTO a convertir
     * @return Collección de instancias de ServiceRequestEntity
     * @generated
     */
    public static List<ServiceRequestEntity> listDTO2Entity(List<ServiceRequestDTO> dtos) {
        List<ServiceRequestEntity> entities = new ArrayList<ServiceRequestEntity>();
        if (dtos != null) {
            for (ServiceRequestDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
