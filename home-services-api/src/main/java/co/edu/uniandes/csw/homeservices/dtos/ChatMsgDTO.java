/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author l.badillo10
 */
@XmlRootElement
public class ChatMsgDTO {
    private Long idChatName;
    private String userName;
    private String userMsg;

    /**
     * @return the idChatName
     */
    public Long getIdChatName() {
        return idChatName;
    }

    /**
     * @param idChatName the idChatName to set
     */
    public void setIdChatName(Long idChatName) {
        this.idChatName = idChatName;
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
     * @return the userMsg
     */
    public String getUserMsg() {
        return userMsg;
    }

    /**
     * @param userMsg the userMsg to set
     */
    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }
    
    
}
