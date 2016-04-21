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

    
}
