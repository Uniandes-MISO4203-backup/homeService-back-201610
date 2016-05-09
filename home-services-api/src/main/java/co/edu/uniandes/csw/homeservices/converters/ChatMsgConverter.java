/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.converters;

import co.edu.uniandes.csw.homeservices.dtos.ChatMsgDTO;
import co.edu.uniandes.csw.homeservices.entities.ChatMsgEntity;
import co.edu.uniandes.csw.homeservices.entities.ChatNameEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author l.badillo10
 */
public abstract class ChatMsgConverter {
    
    private ChatMsgConverter(){
    
    }
    
    
    public static ChatMsgDTO basicEntity2DTO(ChatMsgEntity entity) {
        if (entity != null) {
            ChatMsgDTO dto = new ChatMsgDTO();
            dto.setIdChatName(entity.getChatName().getId());
            dto.setUserName(entity.getUserName());
            dto.setUserMsg(entity.getUserMesg());
           
            
            return dto;
        } else {
            return null;
        }
    }

    private static ChatMsgEntity basicDTO2Entity(ChatMsgDTO dto) {
        if (dto != null) {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setUserName(dto.getUserName());
            entity.setUserMesg(dto.getUserMsg());
            
            return entity;
        } else {
            return null;
        }
    }

    
    public static ChatMsgEntity fullDTO2Entity(ChatMsgDTO dto) {
        if (dto != null) {
            ChatMsgEntity entity = basicDTO2Entity(dto);
            ChatNameEntity con = new ChatNameEntity();
            con.setId(dto.getIdChatName() );            
            entity.setChatName(con);
            return entity;
        } else {
            return null;
        }
    }
    
    public static List<ChatMsgEntity> listDTO2Entity(List<ChatMsgDTO> dtos) {
        List<ChatMsgEntity> entities = new ArrayList<ChatMsgEntity>();
        if (dtos != null) {
            for (ChatMsgDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
    public static List<ChatMsgDTO> listEntity2DTO(List<ChatMsgEntity> entities){
        List<ChatMsgDTO> dtos = new ArrayList<ChatMsgDTO>();
        if (entities != null){
            for (ChatMsgEntity entity : entities ){
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
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
    public static ChatMsgEntity refDTO2Entity(ChatMsgDTO dto) {
        if (dto != null) {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setId(dto.getIdChatName());

            return entity;
        } else {
            return null;
        }
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
    public static ChatMsgDTO refEntity2DTO(ChatMsgEntity entity) {
        if (entity != null) {
            ChatMsgDTO dto = new ChatMsgDTO();
            dto.setIdChatName(entity.getId());
            dto.setUserName(entity.getName());

            return dto;
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
    public static ChatMsgDTO fullEntity2DTO(ChatMsgEntity entity) {
        if (entity != null) {
            ChatMsgDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }
}
