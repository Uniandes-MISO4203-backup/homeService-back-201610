package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.ISkillLogic;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import co.edu.uniandes.csw.homeservices.persistence.SkillPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class SkillLogic implements ISkillLogic {

    @Inject private SkillPersistence persistence;

    /**
     * @generated
     */
    @Override
    public int countSkills() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<SkillEntity> getSkills() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<SkillEntity> getSkills(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public SkillEntity getSkill(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public SkillEntity createSkill(SkillEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public SkillEntity updateSkill(SkillEntity entity) {
        SkillEntity newEntity = entity;
        SkillEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteSkill(Long id) {
        persistence.delete(id);
    }
}
