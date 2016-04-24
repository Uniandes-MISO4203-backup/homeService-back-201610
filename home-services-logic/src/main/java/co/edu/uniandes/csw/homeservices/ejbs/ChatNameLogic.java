package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.IChatNameLogic;
import co.edu.uniandes.csw.homeservices.entities.ChatNameEntity;
import co.edu.uniandes.csw.homeservices.persistence.ChatNamePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ChatNameLogic implements IChatNameLogic {

    @Inject private ChatNamePersistence persistence;

    /**
     * @generated
     */
    @Override
    public int countChats() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<ChatNameEntity> getChats() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<ChatNameEntity> getChats(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public ChatNameEntity getChat(String description) {
        List<ChatNameEntity> listChat =null;
        ChatNameEntity res = null;
        listChat=persistence.findByName(description);
        if(listChat.size()>0)
            res=listChat.get(0);
        return res;
    }

    /**
     * @generated
     */
    @Override
    public ChatNameEntity createChat(ChatNameEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public ChatNameEntity updateChat(ChatNameEntity entity) {
        ChatNameEntity newEntity = entity;
        ChatNameEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteChat(Long id) {
        persistence.delete(id);
    }
}
