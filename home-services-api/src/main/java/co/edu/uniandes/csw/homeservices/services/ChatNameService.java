/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.homeservices.api.IChatNameLogic;
import co.edu.uniandes.csw.homeservices.converters.ChatNameConverter;

import co.edu.uniandes.csw.homeservices.dtos.ChatDTO;
import co.edu.uniandes.csw.homeservices.entities.ChatNameEntity;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author l.badillo10
 */
@Path("/chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatNameService {

    @Context
    private HttpServletRequest req;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    @Inject private IChatNameLogic chatNameLogic;
   

    @POST
    @StatusCreated
    public ChatDTO createChat(ChatDTO dto) {
        ChatDTO res;
        String name= "CU"+dto.getIdCustomer()+"CO"+dto.getIdContractor();
        dto.setName(name);
        ChatNameEntity  ch = ChatNameConverter.fullDTO2Entity(dto);
        ch = chatNameLogic.createChat(ch);
        res= ChatNameConverter.basicEntity2DTO(ch);
        return res;

    }
    @GET
    @Path("{desc}")
    public ChatDTO getChatName(@PathParam("desc") String description){
        ChatDTO res = new  ChatDTO();
        ChatNameEntity entity= chatNameLogic.getChat(description);
        if(entity!=null){
            res = ChatNameConverter.fullEntity2DTO(entity);
        }
        return res;
    }
}
