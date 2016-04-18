/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.homeservices.entities.PriceRequestEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
@Stateless
public class PriceRequestPersistence extends CrudPersistence<PriceRequestEntity>{
    
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
    protected Class<PriceRequestEntity> getEntityClass() {
        return PriceRequestEntity.class;
    }
       
    public List<PriceRequestEntity> getByCustomer(Long idCustomer) {
        Map<String, Object> params = new HashMap<>();
        params.put("customerId",idCustomer);
        return executeListNamedQuery("PriceRequest.getByCustomer", params);
    }
    
    public List<PriceRequestEntity> getByContractor(Long idContractor){
        Map<String, Object> params = new HashMap<>();
        params.put("contractorId",idContractor);
        return executeListNamedQuery("PriceRequest.getByContractor", params);
    }
}
