/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.IPriceRequestLogic;
import co.edu.uniandes.csw.homeservices.entities.PriceRequestEntity;
import co.edu.uniandes.csw.homeservices.persistence.PriceRequestPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author root
 */
@Stateless
public class PriceRequestLogic implements IPriceRequestLogic{
    
    @Inject 
    private PriceRequestPersistence persistence;

    @Override
    public int countPriceRequests() {
       return persistence.count();
    }

    @Override
    public List<PriceRequestEntity> getPriceRequests() {
        return persistence.findAll();
    }

    @Override
    public List<PriceRequestEntity> getPriceRequests(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public PriceRequestEntity getPriceRequest(Long id) {
        return persistence.find(id);
    }

    @Override
    public PriceRequestEntity createPriceRequest(PriceRequestEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public PriceRequestEntity updatePriceRequest(PriceRequestEntity entity) {
        PriceRequestEntity newEntity = entity;
        PriceRequestEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deletePriceRequest(Long id) {
        persistence.delete(id);
    }

    @Override
    public List<PriceRequestEntity> getByContractor(Long idContractor) {
        return persistence.getByContractor(idContractor);
    }
    
    @Override
    public List<PriceRequestEntity> getByServiceRequest(Long customerId) {
        return persistence.getByServiceRequest(customerId);
    }
        
}
