package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.IServiceRequestLogic;
import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import co.edu.uniandes.csw.homeservices.persistence.ServiceRequestPersistence;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import co.edu.uniandes.csw.homeservices.entities.StatusEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ServiceRequestLogic implements IServiceRequestLogic {

    @Inject private ServiceRequestPersistence persistence;

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
        if(!entity.getStatus().getId().equals(StatusEntity.FINISHED)){
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

}
