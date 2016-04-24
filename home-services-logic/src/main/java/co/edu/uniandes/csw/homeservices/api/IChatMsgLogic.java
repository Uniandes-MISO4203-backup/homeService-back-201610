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
    
   
    public int countChatMsgs();
    public List<ChatMsgEntity> getChatMsgs();
    public List<ChatMsgEntity> getChatMsgs(Integer page, Integer maxRecords);
    public ChatMsgEntity getChatMsg(Long id);
    public ChatMsgEntity createChatMsg(ChatMsgEntity entity);
    public ChatMsgEntity updateChatMsg(ChatMsgEntity entity);
    public void deleteChatMsg(Long id);
}
   
