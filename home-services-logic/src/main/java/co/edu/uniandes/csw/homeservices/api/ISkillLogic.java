package co.edu.uniandes.csw.homeservices.api;

import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import java.util.List;

public interface ISkillLogic {
    public int countSkills();
    public List<SkillEntity> getSkills();
    public List<SkillEntity> getSkills(Integer page, Integer maxRecords);
    public SkillEntity getSkill(Long id);
    public SkillEntity createSkill(SkillEntity entity);
    public SkillEntity updateSkill(SkillEntity entity);
    public void deleteSkill(Long id);
}
