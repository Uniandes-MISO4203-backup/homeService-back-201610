package co.edu.uniandes.csw.homeservices.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.homeservices.entities.EducationEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
@Stateless
public class EducationPersistence extends CrudPersistence<EducationEntity>{
    @PersistenceContext(unitName="HomeServicesPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<EducationEntity> getEntityClass() {
        return EducationEntity.class;
    }
}
