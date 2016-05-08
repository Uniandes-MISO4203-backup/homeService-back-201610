package co.edu.uniandes.csw.homeservices.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.homeservices.entities.ReviewEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 * @generated
 */
@Stateless
public class CustomerPersistence extends CrudPersistence<CustomerEntity> {

    @PersistenceContext(unitName="HomeServicesPU")
    protected EntityManager em;
    private static final Logger LOGGER = Logger.getLogger(CustomerPersistence.class.getCanonicalName());

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
    protected Class<CustomerEntity> getEntityClass() {
        return CustomerEntity.class;
    }

    /**
     * Metodo que consulta los reviews de un customer por su identificador
     * @param customerId
     * @return 
     */
    public List<ReviewEntity> getReviewsByCustomer(Long customerId) {
        String queryStr = "SELECT r FROM ReviewEntity r WHERE r.customer.id = :customerId";
        List<ReviewEntity> reviewsList = null;        
        try {
                Query query = em.createQuery(queryStr);
                query.setParameter("customerId", customerId);
                reviewsList = query.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Se produjo un error al tratar de consultar los reviews para el customer:  ".concat(e.getMessage()));
        }        
        return reviewsList;
    }
}
