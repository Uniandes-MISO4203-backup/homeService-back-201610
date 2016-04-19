/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.homeservices.api.IChatMsgLogic;
import co.edu.uniandes.csw.homeservices.converters.ChatMsgConverter;
import co.edu.uniandes.csw.homeservices.dtos.ChatMsgDTO;
import co.edu.uniandes.csw.homeservices.entities.ChatMsgEntity;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author l.badillo10
 */
@Path("/chatmsg")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatMsgService {
    @Context
    private HttpServletRequest req;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    @Inject private IChatMsgLogic chatMsgLogic;
    
    
    
    @POST
    @StatusCreated
    public ChatMsgDTO createMsg(ChatMsgDTO dto) {
        ChatMsgDTO res;
        
        ChatMsgEntity  ch = ChatMsgConverter.fullDTO2Entity(dto);
        ch = chatMsgLogic.createChatMsg(ch);
        res= ChatMsgConverter.basicEntity2DTO(ch);
        return res;

    }
    
    
    
    
}

