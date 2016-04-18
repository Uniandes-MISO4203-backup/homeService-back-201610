/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.api;

import co.edu.uniandes.csw.homeservices.entities.ChatMsgEntity;
import java.util.List;

/**
 *
 * @author l.badillo10
 */
public interface IChatMsgLogic {
    
   
    public ChatMsgEntity createChatMsg(ChatMsgEntity entity);
    public ChatMsgEntity updateChatMsg(ChatMsgEntity entity);
    
    
}
