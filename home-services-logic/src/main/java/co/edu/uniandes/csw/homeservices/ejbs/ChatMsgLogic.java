package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.IChatMsgLogic;
import co.edu.uniandes.csw.homeservices.entities.ChatMsgEntity;
import co.edu.uniandes.csw.homeservices.persistence.ChatMsgPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ChatMsgLogic implements IChatMsgLogic {

    @Inject private ChatMsgPersistence persistence;

    

    

    

    /**
     * @generated
     */
    @Override
    public ChatMsgEntity createChatMsg(ChatMsgEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public ChatMsgEntity updateChatMsg(ChatMsgEntity entity) {
        ChatMsgEntity newEntity = entity;
        ChatMsgEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    
}
