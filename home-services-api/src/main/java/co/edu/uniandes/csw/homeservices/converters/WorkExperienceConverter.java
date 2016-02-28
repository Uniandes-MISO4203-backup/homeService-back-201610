package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.WorkExperienceDTO;
import co.edu.uniandes.csw.homeservices.entities.WorkExperienceEntity;
import java.util.ArrayList;
import java.util.List;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;

/**
 * @generated
 */
public abstract class WorkExperienceConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de Java
     * @generated
     */
    private WorkExperienceConverter() {
    }

    /**
     * Realiza la conversión de WorkExperienceEntity a WorkExperienceDTO.
     * Se invoca cuando otra entidad tiene una referencia a WorkExperienceEntity.
     * Entrega únicamente los atributos proprios de la entidad.
     *
     * @param entity instancia de WorkExperienceEntity a convertir
     * @return instancia de WorkExperienceDTO con los datos recibidos por parámetro
     * @generated
     */
    public static WorkExperienceDTO refEntity2DTO(WorkExperienceEntity entity) {
        if (entity != null) {
            WorkExperienceDTO dto = new WorkExperienceDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setHours(entity.getHours());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Realiza la conversión de WorkExperienceDTO a WorkExperienceEntity Se invoca cuando otro DTO
     * tiene una referencia a WorkExperienceDTO Convierte únicamente el ID ya que es el
     * único atributo necesario para guardar la relación en la base de datos
     *
     * @param dto instancia de WorkExperienceDTO a convertir
     * @return instancia de WorkExperienceEntity con los datos recibidos por parámetro
     * @generated
     */
    public static WorkExperienceEntity refDTO2Entity(WorkExperienceDTO dto) {
        if (dto != null) {
            WorkExperienceEntity entity = new WorkExperienceEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de WorkExperienceEntity a WorkExperienceDTO Se invoca cuando se desea
     * consultar la entidad y sus relaciones muchos a uno o uno a uno
     *
     * @param entity instancia de WorkExperienceEntity a convertir
     * @return Instancia de WorkExperienceDTO con los datos recibidos por parámetro
     * @generated
     */
    private static WorkExperienceDTO basicEntity2DTO(WorkExperienceEntity entity) {
        if (entity != null) {
            WorkExperienceDTO dto = new WorkExperienceDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setContractor(ContractorConverter.refEntity2DTO(entity.getContractor()));
            dto.setDescription(entity.getDescription());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de WorkExperienceDTO a WorkExperienceEntity Se invoca cuando se
     * necesita convertir una instancia de WorkExperienceDTO con los atributos propios de
     * la entidad y con las relaciones uno a uno o muchos a uno
     *
     * @param dto instancia de WorkExperienceDTO a convertir
     * @return Instancia de WorkExperienceEntity creada a partir de los datos de dto
     * @generated
     */
    private static WorkExperienceEntity basicDTO2Entity(WorkExperienceDTO dto) {
        if (dto != null) {
            WorkExperienceEntity entity = new WorkExperienceEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setContractor(ContractorConverter.refDTO2Entity(dto.getContractor()));
            entity.setDescription(dto.getDescription());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte instancias de WorkExperienceEntity a WorkExperienceDTO incluyendo sus relaciones
     * Uno a muchos y Muchos a muchos
     *
     * @param entity Instancia de WorkExperienceEntity a convertir
     * @return Instancia de WorkExperienceDTO con los datos recibidos por parámetro
     * @generated
     */
    public static WorkExperienceDTO fullEntity2DTO(WorkExperienceEntity entity) {
        if (entity != null) {
            WorkExperienceDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * Convierte una instancia de WorkExperienceDTO a WorkExperienceEntity.
     * Incluye todos los atributos de WorkExperienceEntity.
     *
     * @param dto Instancia de WorkExperienceDTO a convertir
     * @return Instancia de WorkExperienceEntity con los datos recibidos por parámetro
     * @generated
     */
    public static WorkExperienceEntity fullDTO2Entity(WorkExperienceDTO dto) {
        if (dto != null) {
            WorkExperienceEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * Convierte una colección de instancias de WorkExperienceEntity a WorkExperienceDTO. Para cada
     * instancia de WorkExperienceEntity en la lista, invoca basicEntity2DTO y añade el
     * nuevo WorkExperienceDTO a una nueva lista
     *
     * @param entities Colección de entidades a convertir
     * @return Collección de instancias de WorkExperienceDTO
     * @generated
     */
    public static List<WorkExperienceDTO> listEntity2DTO(List<WorkExperienceEntity> entities) {
        List<WorkExperienceDTO> dtos = new ArrayList<WorkExperienceDTO>();
        if (entities != null) {
            for (WorkExperienceEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Convierte una colección de instancias de WorkExperienceDTO a instancias de
     * WorkExperienceEntity Para cada instancia se invoca el método basicDTO2Entity
     *
     * @param dtos entities Colección de WorkExperienceDTO a convertir
     * @return Collección de instancias de WorkExperienceEntity
     * @generated
     */
    public static List<WorkExperienceEntity> listDTO2Entity(List<WorkExperienceDTO> dtos) {
        List<WorkExperienceEntity> entities = new ArrayList<WorkExperienceEntity>();
        if (dtos != null) {
            for (WorkExperienceDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    /**
     * Convierte una instancia de WorkExperienceDTO a WorkExperienceEntity asignando un valor
     * al atributo org.eclipse.uml2.uml.internal.impl.PropertyImpl@1ac5e0e1 (name: contractor, visibility: <unset>) (isLeaf: false) (isStatic: false) (isOrdered: false, isUnique: true, isReadOnly: false) (aggregation: none, isDerived: false, isDerivedUnion: false, isID: false) de WorkExperienceEntity. Se usa cuando se necesita convertir
     * un WorkExperienceDTO asignando el libro asociado
     * @param dto Instancia de WorkExperienceDTO
     * @param parent Instancia de ContractorEntity
     * @return Instancia de WorkExperienceEntity con ContractorEntity asociado
     * @generated
     */
    public static WorkExperienceEntity childDTO2Entity(WorkExperienceDTO dto, ContractorEntity parent){
        WorkExperienceEntity entity = basicDTO2Entity(dto);
        entity.setContractor(parent);
        return entity;
    }

    /**
     * Convierte una colección de instancias de WorkExperienceDTO a WorkExperienceEntity
     * asignando el mismo padre para todos. Se usa cuando se necesita crear o
     * actualizar varios WorkExperienceEntity con el mismo org.eclipse.uml2.uml.internal.impl.PropertyImpl@1ac5e0e1 (name: contractor, visibility: <unset>) (isLeaf: false) (isStatic: false) (isOrdered: false, isUnique: true, isReadOnly: false) (aggregation: none, isDerived: false, isDerivedUnion: false, isID: false)
     * @param dtos Colección de instancias de WorkExperienceDTO
     * @param parent Instancia de ContractorEntity
     * @return Colección de WorkExperienceEntity con el atributo org.eclipse.uml2.uml.internal.impl.PropertyImpl@1ac5e0e1 (name: contractor, visibility: <unset>) (isLeaf: false) (isStatic: false) (isOrdered: false, isUnique: true, isReadOnly: false) (aggregation: none, isDerived: false, isDerivedUnion: false, isID: false) asignado
     * @generated
     */
    public static List<WorkExperienceEntity> childListDTO2Entity(List<WorkExperienceDTO> dtos, ContractorEntity parent) {
        List<WorkExperienceEntity> entities = new ArrayList<WorkExperienceEntity>();
        if (dtos != null) {
            for (WorkExperienceDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
}
