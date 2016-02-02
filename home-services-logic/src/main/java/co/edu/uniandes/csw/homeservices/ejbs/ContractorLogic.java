package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.IContractorLogic;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.persistence.ContractorPersistence;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ContractorLogic implements IContractorLogic {

    @Inject private ContractorPersistence persistence;

    /**
     * @generated
     */
    @Override
    public int countContractors() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<ContractorEntity> getContractors() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<ContractorEntity> getContractors(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public ContractorEntity getContractor(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public ContractorEntity createContractor(ContractorEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public ContractorEntity updateContractor(ContractorEntity entity) {
        ContractorEntity newEntity = entity;
        ContractorEntity oldEntity = persistence.find(entity.getId());
        newEntity.setSkills(oldEntity.getSkills());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteContractor(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<SkillEntity> listSkills(Long contractorId) {
        return persistence.find(contractorId).getSkills();
    }

    /**
     * @generated
     */
    @Override
    public SkillEntity getSkills(Long contractorId, Long skillsId) {
        List<SkillEntity> list = persistence.find(contractorId).getSkills();
        SkillEntity skillsEntity = new SkillEntity();
        skillsEntity.setId(skillsId);
        int index = list.indexOf(skillsEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * @generated
     */
    @Override
    public SkillEntity addSkills(Long contractorId, Long skillsId) {
        ContractorEntity contractorEntity = persistence.find(contractorId);
        SkillEntity skillsEntity = new SkillEntity();
        skillsEntity.setId(skillsId);
        contractorEntity.getSkills().add(skillsEntity);
        return getSkills(contractorId, skillsId);
    }

    /**
     * @generated
     */
    @Override
    public List<SkillEntity> replaceSkills(Long contractorId, List<SkillEntity> list) {
        ContractorEntity contractorEntity = persistence.find(contractorId);
        contractorEntity.setSkills(list);
        return contractorEntity.getSkills();
    }

    /**
     * @generated
     */
    @Override
    public void removeSkills(Long contractorId, Long skillsId) {
        ContractorEntity entity = persistence.find(contractorId);
        SkillEntity skillsEntity = new SkillEntity();
        skillsEntity.setId(skillsId);
        entity.getSkills().remove(skillsEntity);
    }
}
