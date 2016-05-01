package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.ContractorDTO;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class ContractorConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private ContractorConverter() {
    }

    /**
     * Realiza la conversión de ContractorEntity a ContractorDTO.
     * Se invoca cuando otra entidad tiene una referencia a ContractorEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de ContractorEntity a convertir
     * @return instancia de ContractorDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ContractorDTO refEntity2DTO(ContractorEntity entity) {
        if (entity != null) {
            ContractorDTO dto = new ContractorDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setLastName(entity.getLastName());
            dto.setDocument(entity.getDocument());
            dto.setPicture(entity.getPicture());
            dto.setCity(entity.getCity());
            dto.setTelefono(entity.getTelefono());
            dto.setEmail(entity.getEmail());
            dto.setUrl(entity.getUrl());
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de ContractorDTO a ContractorEntity Se invoca cuando otro DTO
     * tiene una referencia a ContractorDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de ContractorDTO a convertir
     * @return instancia de ContractorEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ContractorEntity refDTO2Entity(ContractorDTO dto) {
        if (dto != null) {
            ContractorEntity entity = new ContractorEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ContractorEntity a ContractorDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de ContractorEntity a convertir
     * @return Instancia de ContractorDTO con los datos recibidos por parámetro
     * @generated
     */
    private static ContractorDTO basicEntity2DTO(ContractorEntity entity) {
        if (entity != null) {
            ContractorDTO dto = new ContractorDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setLastName(entity.getLastName());
            dto.setDocument(entity.getDocument());
            dto.setPicture(entity.getPicture());
            dto.setCity(entity.getCity());
            dto.setTelefono(entity.getTelefono());
            dto.setEmail(entity.getEmail());
            dto.setUrl(entity.getUrl());
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ContractorDTO a ContractorEntity Se invoca cuando se
     * necesita convertir una instancia de ContractorDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de ContractorDTO a convertir
     * @return Instancia de ContractorEntity creada a partir de los datos de dto
     * @generated
     */
    private static ContractorEntity basicDTO2Entity(ContractorDTO dto) {
        if (dto != null) {
            ContractorEntity entity = new ContractorEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setLastName(dto.getLastName());
            entity.setDocument(dto.getDocument());
            entity.setPicture(dto.getPicture());
            entity.setCity(dto.getCity());
            entity.setTelefono(dto.getTelefono());
            entity.setEmail(dto.getEmail());
            if(dto.getUrl().length() == 11){
              entity.setUrl(dto.getUrl());
            }else{
              if(dto.getUrl().length()>11){
                 entity.setUrl(dto.getUrl().substring(dto.getUrl().length() - 11));
                }
            }
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de ContractorEntity a ContractorDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de ContractorEntity a convertir
     * @return Instancia de ContractorDTO con los datos recibidos por parámetro
     * @generated
     */
    public static ContractorDTO fullEntity2DTO(ContractorEntity entity) {
        if (entity != null) {
            ContractorDTO dto = basicEntity2DTO(entity);
            dto.setSkills(SkillConverter.listEntity2DTO(entity.getSkills()));
            dto.setWorkExperiences(WorkExperienceConverter.listEntity2DTO(entity.getWorkExperiences()));
            dto.setEducations(EducationConverter.listEntity2DTO(entity.getEducations()));
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de ContractorDTO a ContractorEntity.
     * Incluye todos los atributos de ContractorEntity.
     *
     * @param dto Instancia de ContractorDTO a convertir
     * @return Instancia de ContractorEntity con los datos recibidos por parámetro
     * @generated
     */
    public static ContractorEntity fullDTO2Entity(ContractorDTO dto) {
        if (dto != null) {
            ContractorEntity entity = basicDTO2Entity(dto);
            entity.setSkills(SkillConverter.listDTO2Entity(dto.getSkills()));
            entity.setWorkExperiences(WorkExperienceConverter.childListDTO2Entity(dto.getWorkExperiences(), entity));
            entity.setEducations(EducationConverter.childListDTO2Entity(dto.getEducations(), entity));
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de ContractorEntity a ContractorDTO. Para cada
     * instancia de ContractorEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo ContractorDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de ContractorDTO
     * @generated
     */
    public static List<ContractorDTO> listEntity2DTO(List<ContractorEntity> entities) {
        List<ContractorDTO> dtos = new ArrayList<ContractorDTO>();
        if (entities != null) {
            for (ContractorEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de ContractorDTO a instancias de
     * ContractorEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de ContractorDTO a convertir
     * @return Collección de instancias de ContractorEntity
     * @generated
     */
    public static List<ContractorEntity> listDTO2Entity(List<ContractorDTO> dtos) {
        List<ContractorEntity> entities = new ArrayList<ContractorEntity>();
        if (dtos != null) {
            for (ContractorDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}