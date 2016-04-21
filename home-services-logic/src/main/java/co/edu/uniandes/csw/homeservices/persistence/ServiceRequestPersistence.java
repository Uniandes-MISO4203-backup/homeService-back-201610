package co.edu.uniandes.csw.homeservices.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @generated
 */
@Stateless
public class ServiceRequestPersistence extends CrudPersistence<ServiceRequestEntity> {

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
    protected Class<ServiceRequestEntity> getEntityClass() {
        return ServiceRequestEntity.class;
    }
    
    public List<ServiceRequestEntity> getByDescription(String description, Long idCustomer) {
        Map<String, Object> params = new HashMap<>();
        params.put("description", "%" + description.toUpperCase() + "%");
        params.put("customerId",idCustomer);
        return executeListNamedQuery("Service.getByDescription", params);
    }
}
