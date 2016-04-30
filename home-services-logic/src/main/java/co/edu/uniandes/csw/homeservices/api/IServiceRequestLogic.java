package co.edu.uniandes.csw.homeservices.api;

import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import java.util.List;

public interface IServiceRequestLogic {
    public int countServiceRequests();
    public List<ServiceRequestEntity> getServiceRequests();
    public List<ServiceRequestEntity> getServiceRequests(Integer page, Integer maxRecords);
    public ServiceRequestEntity getServiceRequest(Long id);
    public ServiceRequestEntity createServiceRequest(ServiceRequestEntity entity);
    public ServiceRequestEntity updateServiceRequest(ServiceRequestEntity entity);
    public void deleteServiceRequest(Long id);
    public List<SkillEntity> listExpectedskills(Long serviceRequestId);
    public SkillEntity getExpectedskills(Long serviceRequestId, Long expectedskillsId);
    public SkillEntity addExpectedskills(Long serviceRequestId, Long expectedskillsId);
    public List<SkillEntity> replaceExpectedskills(Long serviceRequestId, List<SkillEntity> list);
    public void removeExpectedskills(Long serviceRequestId, Long expectedskillsId);
    public List<ServiceRequestEntity> getByDescription(String description, Long customerId);
    public ServiceRequestEntity finishContract(Long serviceRequestId);
    public ServiceRequestEntity setHire(Long srId, Long prId);
}
