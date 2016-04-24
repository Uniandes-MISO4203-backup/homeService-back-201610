package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.ICustomerLogic;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import co.edu.uniandes.csw.homeservices.persistence.CustomerPersistence;
import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import co.edu.uniandes.csw.homeservices.api.IServiceRequestLogic;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class CustomerLogic implements ICustomerLogic {

    @Inject private CustomerPersistence persistence;

    @Inject private IServiceRequestLogic serviceRequestLogic;

    /**
     * @generated
     */
    @Override
    public int countCustomers() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<CustomerEntity> getCustomers() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<CustomerEntity> getCustomers(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public CustomerEntity getCustomer(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public CustomerEntity createCustomer(CustomerEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public CustomerEntity updateCustomer(CustomerEntity entity) {
        CustomerEntity newEntity = entity;
        CustomerEntity oldEntity = persistence.find(entity.getId());
        newEntity.setServiceRequests(oldEntity.getServiceRequests());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteCustomer(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<ServiceRequestEntity> listServiceRequests(Long customerId) {
        return persistence.find(customerId).getServiceRequests();
    }

    /**
     * @generated
     */
    @Override
    public ServiceRequestEntity getServiceRequests(Long customerId, Long serviceRequestsId) {
        List<ServiceRequestEntity> list = persistence.find(customerId).getServiceRequests();
        ServiceRequestEntity serviceRequestsEntity = new ServiceRequestEntity();
        serviceRequestsEntity.setId(serviceRequestsId);
        int index = list.indexOf(serviceRequestsEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * @generated
     */
    @Override
    public ServiceRequestEntity addServiceRequests(Long customerId, Long serviceRequestsId) {
        CustomerEntity customerEntity = persistence.find(customerId);
        ServiceRequestEntity serviceRequestsEntity = serviceRequestLogic.getServiceRequest(serviceRequestsId);
        serviceRequestsEntity.setCustomer(customerEntity);
        return serviceRequestsEntity;
    }

    /**
     * @generated
     */
    @Override
    public List<ServiceRequestEntity> replaceServiceRequests(Long customerId, List<ServiceRequestEntity> list) {
        CustomerEntity customerEntity = persistence.find(customerId);
        List<ServiceRequestEntity> serviceRequestList = serviceRequestLogic.getServiceRequests();
        for (ServiceRequestEntity serviceRequest : serviceRequestList) {
            if (list.contains(serviceRequest)) {
                serviceRequest.setCustomer(customerEntity);
            } else {
                if (serviceRequest.getCustomer() != null && serviceRequest.getCustomer().equals(customerEntity)) {
                    serviceRequest.setCustomer(null);
                }
            }
        }
        customerEntity.setServiceRequests(list);
        return customerEntity.getServiceRequests();
    }

    /**
     * @generated
     */
    @Override
    public void removeServiceRequests(Long customerId, Long serviceRequestsId) {
        ServiceRequestEntity entity = serviceRequestLogic.getServiceRequest(serviceRequestsId);
        entity.setCustomer(null);
    }
}
