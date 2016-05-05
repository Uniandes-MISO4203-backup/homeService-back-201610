package co.edu.uniandes.csw.homeservices.api;

import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.entities.ReviewEntity;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import java.util.List;

public interface IContractorLogic {
    public int countContractors();
    public List<ContractorEntity> getContractors();
    public List<ContractorEntity> getContractors(Integer page, Integer maxRecords);
    public ContractorEntity getContractor(Long id);
    public ContractorEntity createContractor(ContractorEntity entity);
    public ContractorEntity updateContractor(ContractorEntity entity);
    public void deleteContractor(Long id);
    public List<SkillEntity> listSkills(Long contractorId);
    public SkillEntity getSkills(Long contractorId, Long skillsId);
    public SkillEntity addSkills(Long contractorId, Long skillsId);
    public List<SkillEntity> replaceSkills(Long contractorId, List<SkillEntity> list);
    public void removeSkills(Long contractorId, Long skillsId);
    public List<ContractorEntity> getContractorsBySkill(String skill);
    public List<ContractorEntity> getContractorsByExperience(String skill);
    public List<ContractorEntity> getContractorsBySkillServiceReq(int serviceReqId);
    public List<ContractorEntity> getContractorsBySkillServiceReqAndCreatePriceRequest(int contractorId);
    public List<ReviewEntity> getReviews(Long contractorId);
}
