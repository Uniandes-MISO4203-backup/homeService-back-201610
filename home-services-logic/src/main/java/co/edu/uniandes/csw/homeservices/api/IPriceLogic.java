/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.api;

import co.edu.uniandes.csw.homeservices.entities.PriceEntity;
import java.util.List;

/**
 *
 * @author juan camilo cerquera lozada <jc.cerquera@uniandes.edu.co>
 */
public interface IPriceLogic {
    public int countPrices();
    public List<PriceEntity> getPrices();
    public List<PriceEntity> getPrices(Integer page, Integer maxRecords);
    public PriceEntity getPrice(Long id);
    public PriceEntity createPrice(PriceEntity entity);
    public PriceEntity updatePrice(PriceEntity entity);
    public void deletePrice(Long id);
    public List<PriceEntity> getByServiceRequest(Long customerId);
}
