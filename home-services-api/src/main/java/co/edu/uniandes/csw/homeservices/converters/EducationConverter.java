package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.EducationDTO;
import co.edu.uniandes.csw.homeservices.entities.EducationEntity;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
public class EducationConverter {
    
    private EducationConverter(){
        //este constructor tendra un uso futuro
    }

    public static EducationDTO refEntity2DTO(EducationEntity entity) {
        if (entity != null) {
            EducationDTO dto = new EducationDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setInstitute(entity.getInstitute());
            dto.setYear(entity.getEndYear());
            return dto;
        } else {
            return null;
        }
    }
    
    public static EducationEntity refDTO2Entity(EducationDTO dto) {
        if (dto != null) {
            EducationEntity entity = new EducationEntity();
            entity.setId(dto.getId());
            return entity;
        } else {
            return null;
        }
    }
    
    private static EducationDTO basicEntity2DTO(EducationEntity entity) {
        if (entity != null) {
            EducationDTO dto = new EducationDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setInstitute(entity.getInstitute());
            dto.setYear(entity.getEndYear());
            dto.setContractor(ContractorConverter.refEntity2DTO(entity.getContractor()));
            return dto;
        } else {
            return null;
        }
    }
    
     private static EducationEntity basicDTO2Entity(EducationDTO dto) {
        if (dto != null) {
            EducationEntity entity = new EducationEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setInstitute(dto.getInstitute());
            entity.setEndYear(dto.getYear());
            entity.setContractor(ContractorConverter.refDTO2Entity(dto.getContractor()));
            return entity;
        } else {
            return null;
        }
    }
     
    public static EducationDTO fullEntity2DTO(EducationEntity entity) {
        if (entity != null) {
            return basicEntity2DTO(entity);
        } else {
            return null;
        }
    }
    
    public static EducationEntity fullDTO2Entity(EducationDTO dto) {
        if (dto != null) {
            return basicDTO2Entity(dto);
        } else {
            return null;
        }
    }
    
    public static List<EducationDTO> listEntity2DTO(List<EducationEntity> entities) {
        List<EducationDTO> dtos = new ArrayList<EducationDTO>();
        if (entities != null) {
            for (EducationEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }
    
    public static List<EducationEntity> listDTO2Entity(List<EducationDTO> dtos) {
        List<EducationEntity> entities = new ArrayList<EducationEntity>();
        if (dtos != null) {
            for (EducationDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
    public static EducationEntity childDTO2Entity(EducationDTO dto, ContractorEntity parent){
        EducationEntity entity = basicDTO2Entity(dto);
        entity.setContractor(parent);
        return entity;
    }
    
    public static List<EducationEntity> childListDTO2Entity(List<EducationDTO> dtos, ContractorEntity parent) {
        List<EducationEntity> entities = new ArrayList<EducationEntity>();
        if (dtos != null) {
            for (EducationDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
}
