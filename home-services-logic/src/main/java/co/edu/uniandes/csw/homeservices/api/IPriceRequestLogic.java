/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.api;

import co.edu.uniandes.csw.homeservices.entities.PriceRequestEntity;
import java.util.List;

/**
 *
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
public interface IPriceRequestLogic {
    
    public int countPriceRequests();
    public List<PriceRequestEntity> getPriceRequests();
    public List<PriceRequestEntity> getPriceRequests(Integer page, Integer maxRecords);
    public PriceRequestEntity getPriceRequest(Long id);
    public PriceRequestEntity createPriceRequest(PriceRequestEntity entity);
    public PriceRequestEntity updatePriceRequest(PriceRequestEntity entity);
    public void deletePriceRequest(Long id);
    public List<PriceRequestEntity> getByContractor(Long idContractor);
    public List<PriceRequestEntity> getByServiceRequest(Long customerId);
    
}
