/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.api;

import co.edu.uniandes.csw.homeservices.entities.CalificationServiceEntity;
import java.util.List;

/**
 *
 * @author l.badillo10
 */
public interface ICalificationServiceLogic {
    public int countCalifications();
    public List<CalificationServiceEntity> getCalifications();
    public List<CalificationServiceEntity> getCalifications(Integer page, Integer maxRecords);
    public CalificationServiceEntity getCalification(Long id);
    public CalificationServiceEntity createCalification(CalificationServiceEntity entity);
    public CalificationServiceEntity updateCalification(CalificationServiceEntity entity);
    public void deleteCalification(Long id);
    
}
