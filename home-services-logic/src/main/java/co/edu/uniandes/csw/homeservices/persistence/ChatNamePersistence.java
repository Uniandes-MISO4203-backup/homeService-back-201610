/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.homeservices.entities.ChatNameEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author l.badillo10
 */
public class ChatNamePersistence extends CrudPersistence<ChatNameEntity> {
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
    protected Class<ChatNameEntity> getEntityClass() {
        return ChatNameEntity.class;
    }
    
}
