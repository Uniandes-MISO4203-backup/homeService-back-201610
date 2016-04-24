/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.api;

import co.edu.uniandes.csw.homeservices.entities.ChatNameEntity;
import java.util.List;

/**
 *
 * @author l.badillo10
 */
public interface IChatNameLogic {
    public int countChats();
    public List<ChatNameEntity> getChats();
    public List<ChatNameEntity> getChats(Integer page, Integer maxRecords);
    public ChatNameEntity getChat(String description);
    public ChatNameEntity createChat(ChatNameEntity entity);
    public ChatNameEntity updateChat(ChatNameEntity entity);
    public void deleteChat(Long id);
    
}
