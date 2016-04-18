/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author l.badillo10
 */
@Entity

public class ChatMsgEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
    @ManyToOne
    private ChatNameEntity chatName;
    
    private String userName;
    private String userMesg;

    /**
     * @return the chatName
     */
    public ChatNameEntity getChatName() {
        return chatName;
    }

    /**
     * @param chatName the chatName to set
     */
    public void setChatName(ChatNameEntity chatName) {
        this.chatName = chatName;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userMesg
     */
    public String getUserMesg() {
        return userMesg;
    }

    /**
     * @param userMesg the userMesg to set
     */
    public void setUserMesg(String userMesg) {
        this.userMesg = userMesg;
    }
    
    
    
    
}
