/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.ChatDTO;
import co.edu.uniandes.csw.homeservices.dtos.ChatMsgDTO;
import co.edu.uniandes.csw.homeservices.entities.ChatMsgEntity;
import co.edu.uniandes.csw.homeservices.entities.ChatNameEntity;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author l.badillo10
 */
public abstract class ChatNameConverter {
    
    private ChatNameConverter(){
    }
    

    
    public static ChatDTO basicEntity2DTO(ChatNameEntity entity) {
        if (entity != null) {
            ChatDTO dto = new ChatDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCreationDate(entity.getCreationDate());
            
            return dto;
        } else {
            return null;
        }
    }
    
    public static ChatDTO fullEntity2DTO(ChatNameEntity entity){
        ChatDTO res= null;
        List<ChatMsgDTO> listMsgDTO;
        List<ChatMsgEntity> listMsgEntity;
        if (entity != null){
            res= basicEntity2DTO(entity);
            listMsgEntity = entity.getChatMsgs();
            listMsgDTO=ChatMsgConverter.listEntity2DTO(listMsgEntity);
            res.setListChatMsg(listMsgDTO);
        }
        return res;
            
        
    }

    private static ChatNameEntity basicDTO2Entity(ChatDTO dto) {
        if (dto != null) {
            ChatNameEntity entity = new ChatNameEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            
            entity.setCreationDate(dto.getCreationDate());
            
            return entity;
        } else {
            return null;
        }
    }

    
    public static ChatNameEntity fullDTO2Entity(ChatDTO dto) {
        if (dto != null) {
            ChatNameEntity entity = basicDTO2Entity(dto);
            ContractorEntity con = new ContractorEntity();
            con.setId(dto.getIdContractor());            
            entity.setContractor(con);
            CustomerEntity cus = new CustomerEntity();
            cus.setId(dto.getIdCustomer());
            entity.setCustomer(cus);
            return entity;
        } else {
            return null;
        }
    }

    public static List<ChatDTO> listEntity2DTO(List<ChatNameEntity> entities) {
          List<ChatDTO> dtos = new ArrayList<ChatDTO>();
        if (entities != null) {
            for (ChatNameEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
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
    public static ChatDTO refEntity2DTO(ChatNameEntity entity) {
        if (entity != null) {
            ChatDTO dto = new ChatDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());

            return dto;
        } else {
            return null;
        }
    }
    
    public static List<ChatNameEntity> listDTO2Entity(List<ChatDTO> dtos) {
        List<ChatNameEntity> entities = new ArrayList<ChatNameEntity>();
        if (dtos != null) {
            for (ChatDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
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
    public static ChatNameEntity refDTO2Entity(ChatDTO dto) {
        if (dto != null) {
            ChatNameEntity entity = new ChatNameEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }
    
}
