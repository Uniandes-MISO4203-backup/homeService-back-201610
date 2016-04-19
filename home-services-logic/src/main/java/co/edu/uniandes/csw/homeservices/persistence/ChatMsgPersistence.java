/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.homeservices.entities.ChatMsgEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author l.badillo10
 */
public class ChatMsgPersistence extends CrudPersistence<ChatMsgEntity> {
    @PersistenceContext(unitName="HomeServicesPU")
    protected EntityManager em;

    /**
     * @generated
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @generated
     */
    @Override
    protected Class<ChatMsgEntity> getEntityClass() {
        return ChatMsgEntity.class;
    }
    
    public List<ChatMsgEntity> getListByChatId( Long chatNameId) {
        Map<String, Object> params = new HashMap<>();
        params.put("chatNameId",chatNameId);
        return executeListNamedQuery("ChatMsg.getByChatNameId", params);
    }
    
}

