package co.edu.uniandes.csw.homeservices.api;

import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import co.edu.uniandes.csw.homeservices.entities.ReviewEntity;
import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import java.util.List;

public interface ICustomerLogic {
    public int countCustomers();
    public List<CustomerEntity> getCustomers();
    public List<CustomerEntity> getCustomers(Integer page, Integer maxRecords);
    public CustomerEntity getCustomer(Long id);
    public CustomerEntity createCustomer(CustomerEntity entity);
    public CustomerEntity updateCustomer(CustomerEntity entity);
    public void deleteCustomer(Long id);
    public List<ServiceRequestEntity> listServiceRequests(Long customerId);
    public ServiceRequestEntity getServiceRequests(Long customerId, Long serviceRequestsId);
    public ServiceRequestEntity addServiceRequests(Long customerId, Long serviceRequestsId);
    public List<ServiceRequestEntity> replaceServiceRequests(Long customerId, List<ServiceRequestEntity> list);
    public void removeServiceRequests(Long customerId, Long serviceRequestsId);
    public List<ReviewEntity> getReviews(Long customerId);
}
