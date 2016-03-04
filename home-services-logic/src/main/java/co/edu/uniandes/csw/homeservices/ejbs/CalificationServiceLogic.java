/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.ICalificationServiceLogic;
import co.edu.uniandes.csw.homeservices.entities.CalificationServiceEntity;
import co.edu.uniandes.csw.homeservices.persistence.CalificationServicePersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author l.badillo10
 */
public class CalificationServiceLogic implements ICalificationServiceLogic {
    @Inject private CalificationServicePersistence persistence;

    @Override
    public int countCalifications() {
        return persistence.count();
    }

    @Override
    public List<CalificationServiceEntity> getCalifications() {
         return persistence.findAll();
    }

    @Override
    public List<CalificationServiceEntity> getCalifications(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public CalificationServiceEntity getCalification(Long id) {
        return persistence.find(id);
    }

    @Override
    public CalificationServiceEntity createCalification(CalificationServiceEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public CalificationServiceEntity updateCalification(CalificationServiceEntity entity) {
        CalificationServiceEntity newEntity = entity;
        CalificationServiceEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deleteCalification(Long id) {
        persistence.delete(id);
    }
    
    
}
