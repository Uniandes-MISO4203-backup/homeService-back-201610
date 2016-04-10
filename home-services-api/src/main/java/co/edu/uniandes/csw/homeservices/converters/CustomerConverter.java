package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.CustomerDTO;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class CustomerConverter {

    /**
     * Realiza la conversión de CustomerEntity a CustomerDTO.
     * Se invoca cuando otra entidad tiene una referencia a CustomerEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de CustomerEntity a convertir
     * @return instancia de CustomerDTO con los datos recibidos por parámetro
     * @generated
     */
    public static CustomerDTO refEntity2DTO(CustomerEntity entity) {
        if (entity != null) {
            CustomerDTO dto = new CustomerDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setLastName(entity.getLastName());
            dto.setDocument(entity.getDocument());
            dto.setPicture(entity.getPicture());
            dto.setAddress(entity.getAddress());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de CustomerDTO a CustomerEntity Se invoca cuando otro DTO
     * tiene una referencia a CustomerDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de CustomerDTO a convertir
     * @return instancia de CustomerEntity con los datos recibidos por parámetro
     * @generated
     */
    public static CustomerEntity refDTO2Entity(CustomerDTO dto) {
        if (dto != null) {
            CustomerEntity entity = new CustomerEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CustomerEntity a CustomerDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de CustomerEntity a convertir
     * @return Instancia de CustomerDTO con los datos recibidos por parámetro
     * @generated
     */
    private static CustomerDTO basicEntity2DTO(CustomerEntity entity) {
        if (entity != null) {
            CustomerDTO dto = new CustomerDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setLastName(entity.getLastName());
            dto.setDocument(entity.getDocument());
            dto.setPicture(entity.getPicture());
            dto.setAddress(entity.getAddress());
            
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CustomerDTO a CustomerEntity Se invoca cuando se
     * necesita convertir una instancia de CustomerDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de CustomerDTO a convertir
     * @return Instancia de CustomerEntity creada a partir de los datos de dto
     * @generated
     */
    private static CustomerEntity basicDTO2Entity(CustomerDTO dto) {
        if (dto != null) {
            CustomerEntity entity = new CustomerEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setLastName(dto.getLastName());
            entity.setDocument(dto.getDocument());
            entity.setPicture(dto.getPicture());
            entity.setAddress(dto.getAddress());
            
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de CustomerEntity a CustomerDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de CustomerEntity a convertir
     * @return Instancia de CustomerDTO con los datos recibidos por parámetro
     * @generated
     */
    public static CustomerDTO fullEntity2DTO(CustomerEntity entity) {
        if (entity != null) {
            CustomerDTO dto = basicEntity2DTO(entity);
            dto.setServiceRequests(ServiceRequestConverter.listEntity2DTO(entity.getServiceRequests()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de CustomerDTO a CustomerEntity.
     * Incluye todos los atributos de CustomerEntity.
     *
     * @param dto Instancia de CustomerDTO a convertir
     * @return Instancia de CustomerEntity con los datos recibidos por parámetro
     * @generated
     */
    public static CustomerEntity fullDTO2Entity(CustomerDTO dto) {
        if (dto != null) {
            CustomerEntity entity = basicDTO2Entity(dto);
            entity.setServiceRequests(ServiceRequestConverter.listDTO2Entity(dto.getServiceRequests()));
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de CustomerEntity a CustomerDTO. Para cada
     * instancia de CustomerEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo CustomerDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de CustomerDTO
     * @generated
     */
    public static List<CustomerDTO> listEntity2DTO(List<CustomerEntity> entities) {
        List<CustomerDTO> dtos = new ArrayList<CustomerDTO>();
        if (entities != null) {
            for (CustomerEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de CustomerDTO a instancias de
     * CustomerEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de CustomerDTO a convertir
     * @return Collección de instancias de CustomerEntity
     * @generated
     */
    public static List<CustomerEntity> listDTO2Entity(List<CustomerDTO> dtos) {
        List<CustomerEntity> entities = new ArrayList<CustomerEntity>();
        if (dtos != null) {
            for (CustomerDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
