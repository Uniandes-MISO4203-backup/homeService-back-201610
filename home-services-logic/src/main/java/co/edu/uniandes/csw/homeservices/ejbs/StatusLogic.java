package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.IStatusLogic;
import co.edu.uniandes.csw.homeservices.entities.StatusEntity;
import co.edu.uniandes.csw.homeservices.persistence.StatusPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class StatusLogic implements IStatusLogic {

    @Inject private StatusPersistence persistence;

    /**
     * @generated
     */
    @Override
    public int countStatuss() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<StatusEntity> getStatuss() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<StatusEntity> getStatuss(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public StatusEntity getStatus(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public StatusEntity createStatus(StatusEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public StatusEntity updateStatus(StatusEntity entity) {
        StatusEntity newEntity = entity;
        StatusEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteStatus(Long id) {
        persistence.delete(id);
    }
}
