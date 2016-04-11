/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.IPriceLogic;
import co.edu.uniandes.csw.homeservices.entities.PriceEntity;
import co.edu.uniandes.csw.homeservices.persistence.PricePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
@Stateless
public class PriceLogic implements IPriceLogic{
    
     @Inject private PricePersistence persistence;

    @Override
    public int countPrices() {
        return persistence.count();
    }

    @Override
    public List<PriceEntity> getPrices() {
       return persistence.findAll();
    }

    @Override
    public List<PriceEntity> getPrices(Integer page, Integer maxRecords) {
       return persistence.findAll(page, maxRecords);
    }

    @Override
    public PriceEntity getPrice(Long id) {
       return persistence.find(id);
    }

    @Override
    public PriceEntity createPrice(PriceEntity entity) {
         persistence.create(entity);
        return entity;
    }

    @Override
    public PriceEntity updatePrice(PriceEntity entity) {
        PriceEntity newEntity = entity;
        PriceEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deletePrice(Long id) {
         persistence.delete(id);
    }

    @Override
    public List<PriceEntity> getByServiceRequest(Long customerId) {
        return persistence.getByServiceRequest(customerId);
    }    
}
