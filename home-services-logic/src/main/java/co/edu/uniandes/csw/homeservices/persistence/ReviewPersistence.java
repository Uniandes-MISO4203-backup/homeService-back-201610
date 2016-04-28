package co.edu.uniandes.csw.homeservices.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.homeservices.entities.ReviewEntity;

/**
 * @generated
 */
@Stateless
public class ReviewPersistence extends CrudPersistence<ReviewEntity> {

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
    protected Class<ReviewEntity> getEntityClass() {
        return ReviewEntity.class;
    }
}
