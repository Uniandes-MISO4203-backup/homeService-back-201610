package co.edu.uniandes.csw.homeservices.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;


/**
 * @generated
 */
@Stateless
public class ContractorPersistence extends CrudPersistence<ContractorEntity> {

    private static final Logger LOGGER = Logger.getLogger(ContractorPersistence.class.getCanonicalName());
    
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
    protected Class<ContractorEntity> getEntityClass() {
        return ContractorEntity.class;
    }
    
    /**
     * Metodo que permite realizar la busqueda de los contractors dado un 
     * skill en string
     * 
     * @param skill
     * @return List<ContractorEntity> encontrados que tienen ese skill 
     *         dentro de sus skill, o lista vacia si no encuentra nada
     */
    public List<ContractorEntity> getContractorsBySkill(String skill) {
        
        List<ContractorEntity> contractorsBySkill = new ArrayList<ContractorEntity>();
        
        if (skill != null && !skill.isEmpty()){
            skill = skill.toUpperCase().trim();     
            String consulta = "SELECT c FROM ContractorEntity c JOIN c.skills skill WHERE UPPER(skill.name) = :skillName";
           
            try {

                Query query = em.createQuery(consulta);
                query.setParameter("skillName", skill);
                contractorsBySkill = query.getResultList();

            } catch (Exception e) {
                LOGGER.severe("Ocurrió un error al consultar los contractor por skill " + e.getMessage());
                return contractorsBySkill;
            }
        }
 
        return contractorsBySkill;
    }
    
    /**
     * Metodo que permite realizar la busqueda de los contractors dado un 
     * skill en string
     * 
     * @param skill
     * @return List<ContractorEntity> encontrados que tienen ese skill 
     *         dentro de sus skill, o lista vacia si no encuentra nada
     */
    public List<ContractorEntity> getContractorsByExperience(String experience) {
        
        List<ContractorEntity> contractorsByExperience = new ArrayList<ContractorEntity>();
        
        if (experience != null && !experience.isEmpty()){    
            String queryStr = "SELECT c FROM ContractorEntity c JOIN c.workExperiences workExperience WHERE workExperience.description = :experience";
           
            try {

                Query query = em.createQuery(queryStr);
                query.setParameter("experience", experience);
                contractorsByExperience = query.getResultList();

            } catch (Exception e) {              
                LOGGER.severe("Error al consultar contractor pot experience " + e.getMessage());
                return contractorsByExperience;
            }
        }
 
        return contractorsByExperience;
    }
    
    
    /**
     * Obtiene los skills de un contractor
     * @param contractorId
     * @return 
     */
    public List<String> getSkillsByContractorId(int contractorId){
      List<SkillEntity> skillEntitys = new ArrayList<>();
      List<String> stringSkills = new ArrayList<>();
      if(contractorId != 0){
          String consulta = "SELECT ce FROM ContractorEntity ce WHERE ce.id = :contractorId";
          Query query = em.createQuery(consulta);
          query.setParameter("contractorId", contractorId);
          ContractorEntity contractorEntity = (ContractorEntity) query.getSingleResult();
          skillEntitys = contractorEntity.getSkills();
          for (SkillEntity skill : skillEntitys) {
               stringSkills.add(skill.getName().toUpperCase().trim());
          }
      }
      return stringSkills;
    }   
    
    /**
     * Obtiene un service request a partir de una lista de Skills
     * @param skills
     * @return serviceRequest
     */
    public ServiceRequestEntity getServiceRequestByContractorSkills(List<String> skills){
        ServiceRequestEntity serviceRequest = null;
                if (skills != null && !skills.isEmpty()){
             
            String consulta = "SELECT sr FROM ServiceRequestEntity sr JOIN sr.expectedskills skill WHERE UPPER(skill.name) in :skills";
           
            try {
                Query query = em.createQuery(consulta);
                query.setParameter("skills", skills);
                 serviceRequest = (ServiceRequestEntity) query.getResultList().get(0);

            } catch (Exception e) {
                return serviceRequest;
            }
        }
                return serviceRequest;
    }
    
    
     /**
     * Obtiene la lista de los registros de contractors los cuales tengan
     * dentro de sus skills alguno que coincida con los skill que se esperan
     * en el service request.
     *
     * @param serviceReqId
     * @return Colección de objetos de ContractorDTO
     */
    public List<ContractorEntity> getContractorsBySkillServiceReq(int serviceReqId) {
        
                
        List<ContractorEntity> contractorsBySkill = new ArrayList<ContractorEntity>();
        ServiceRequestEntity servReq = null;
        
        if (serviceReqId != 0){
                
            String consulta = "SELECT u FROM ServiceRequestEntity u WHERE u.id = :serviceReqId";
           
            try {

                Query query = em.createQuery(consulta);
                query.setParameter("serviceReqId", serviceReqId);
                servReq = (ServiceRequestEntity) query.getSingleResult();
                
                if (servReq != null){
                
                    if(servReq.getExpectedskills() != null && servReq.getExpectedskills().size() > 0){
                        
                        List<String> expectedSkillsNames = new ArrayList<String>();
                        
                        for (SkillEntity skill : servReq.getExpectedskills()) {
                            expectedSkillsNames.add(skill.getName().toUpperCase().trim());
                        }
                        
                        contractorsBySkill = this.getContractorsBySkillsName(expectedSkillsNames);
                        
                    } else {
                        LOGGER.log(Level.WARNING, "El service request consultado por el id " + serviceReqId + " no tiene expected skills asociados");
                    }
                } else {
                    LOGGER.log(Level.WARNING, "El service request consultado por el id " + serviceReqId + " no a arrojado resultados");
                }
 
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Ocurrió un error al consultar los contractor por skills service request ".concat(e.getMessage()));
                return contractorsBySkill;
            }
        }
 
        return contractorsBySkill;
    } 
    
        
    /**
     * Metodo que permite realizar la busqueda de los contractors dado una 
     * lista de skills names
     * 
     * @param List<String >skills names
     * @return List<ContractorEntity> encontrados que tienen ese skill 
     *         dentro de sus skill, o lista vacia si no encuentra nada
     */
    public List<ContractorEntity> getContractorsBySkillsName(List<String> skills) {
        
        List<ContractorEntity> contractorsBySkill = new ArrayList<ContractorEntity>();
        
        if (skills != null && !skills.isEmpty()){
             
            String consulta = "SELECT distinct c FROM ContractorEntity c JOIN c.skills skill WHERE UPPER(skill.name) in :skills";

            try {

                Query query = em.createQuery(consulta);
                query.setParameter("skills", skills);
                contractorsBySkill = query.getResultList();

            } catch (Exception e) {
                return contractorsBySkill;
            }
        }
 
        return contractorsBySkill;
    }
    
}
