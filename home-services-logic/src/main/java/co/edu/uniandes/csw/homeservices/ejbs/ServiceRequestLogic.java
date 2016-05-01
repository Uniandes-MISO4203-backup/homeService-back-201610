package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.IContractorLogic;
import co.edu.uniandes.csw.homeservices.api.IServiceRequestLogic;
import co.edu.uniandes.csw.homeservices.api.IPriceRequestLogic;
import static co.edu.uniandes.csw.homeservices.ejbs.ContractorLogic.sendEmail2;
import co.edu.uniandes.csw.homeservices.entities.PriceRequestEntity;
import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import co.edu.uniandes.csw.homeservices.persistence.ServiceRequestPersistence;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.entities.StatusEntity;
import co.edu.uniandes.csw.homeservices.persistence.StatusPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ServiceRequestLogic implements IServiceRequestLogic {

    @Inject private ServiceRequestPersistence persistence;
    @Inject private StatusPersistence statusPersistence;
    @Inject private IPriceRequestLogic priceRequestLogic;
    
    /**
     * @generated
     */
    @Override
    public int countServiceRequests() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<ServiceRequestEntity> getServiceRequests() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<ServiceRequestEntity> getServiceRequests(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public ServiceRequestEntity getServiceRequest(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public ServiceRequestEntity createServiceRequest(ServiceRequestEntity entity) {
        entity.setStatus(statusPersistence.find(1L));
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */ 
    @Override
    public ServiceRequestEntity updateServiceRequest(ServiceRequestEntity entity) {
        ServiceRequestEntity newEntity = entity;
        ServiceRequestEntity oldEntity = persistence.find(entity.getId());
        newEntity.setExpectedskills(oldEntity.getExpectedskills());
        if(entity.getStatus()!=null && !entity.getStatus().getId().equals(StatusEntity.FINISHED)){
            //keep score null while state is not finished
            entity.setScore(null);
        }
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteServiceRequest(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<SkillEntity> listExpectedskills(Long serviceRequestId) {
        return persistence.find(serviceRequestId).getExpectedskills();
    }

    /**
     * @generated
     */
    @Override
    public SkillEntity getExpectedskills(Long serviceRequestId, Long expectedskillsId) {
        List<SkillEntity> list = persistence.find(serviceRequestId).getExpectedskills();
        SkillEntity expectedskillsEntity = new SkillEntity();
        expectedskillsEntity.setId(expectedskillsId);
        int index = list.indexOf(expectedskillsEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * @generated
     */
    @Override
    public SkillEntity addExpectedskills(Long serviceRequestId, Long expectedskillsId) {
        ServiceRequestEntity serviceRequestEntity = persistence.find(serviceRequestId);
        SkillEntity expectedskillsEntity = new SkillEntity();
        expectedskillsEntity.setId(expectedskillsId);
        serviceRequestEntity.getExpectedskills().add(expectedskillsEntity);
        return getExpectedskills(serviceRequestId, expectedskillsId);
    }

    /**
     * @generated
     */
    @Override
    public List<SkillEntity> replaceExpectedskills(Long serviceRequestId, List<SkillEntity> list) {
        ServiceRequestEntity serviceRequestEntity = persistence.find(serviceRequestId);
        serviceRequestEntity.setExpectedskills(list);
        return serviceRequestEntity.getExpectedskills();
    }

    /**
     * @generated
     */
    @Override
    public void removeExpectedskills(Long serviceRequestId, Long expectedskillsId) {
        ServiceRequestEntity entity = persistence.find(serviceRequestId);
        SkillEntity expectedskillsEntity = new SkillEntity();
        expectedskillsEntity.setId(expectedskillsId);
        entity.getExpectedskills().remove(expectedskillsEntity);
    }
    
    @Override
    public List<ServiceRequestEntity> getByDescription(String description, Long customerId) {
        return persistence.getByDescription(description, customerId);
    }

    /**
     * Metodo que permite cambiar el estado de un service request a finalizado
     * @param serviceRequestId
     * @return serviceRequest actualizado
     */
    @Override
    public ServiceRequestEntity finishContract(Long serviceRequestId) {
        ServiceRequestEntity serviceRequest = persistence.find(serviceRequestId);      
        StatusEntity finishStatus = statusPersistence.find(StatusEntity.FINISHED);       
        serviceRequest.setStatus(finishStatus);
        
        return persistence.update(serviceRequest);
    }

    /*
     * @generated
     */
    @Override
    public ServiceRequestEntity setHire(Long srId, Long prId) {

        List<PriceRequestEntity> lisPriceRequestEntity = priceRequestLogic.getByServiceRequest(srId);
        
        for (PriceRequestEntity priceEntity : lisPriceRequestEntity){
        
            String emailTo = priceEntity.getContractor().getEmail();
            String nameTo =  priceEntity.getContractor().getName();
            char indicador= ' ';
            
            if (priceEntity.getId()==prId) {
                
                priceEntity.setStatus("CONTRATADO");
                indicador='C';
                                
            } else {
              
                priceEntity.setStatus("RECHAZADO");
                indicador='R';
                
            }

            try {
                sendEmail2(emailTo, nameTo, indicador);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            } 
            
            priceRequestLogic.updatePriceRequest(priceEntity);
        
        }
    
        ServiceRequestEntity serviceEntity = persistence.find(srId);
        
        StatusEntity status = new StatusEntity();
        status.setId((long)2);
        serviceEntity.setStatus(status);
        
        ServiceRequestEntity resServiceRequest = updateServiceRequest(serviceEntity);            

        return resServiceRequest;
    
    }
}
