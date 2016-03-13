package co.edu.uniandes.csw.homeservices.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 * @generated
 */
@Stateless
public class ContractorPersistence extends CrudPersistence<ContractorEntity> {

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
                System.out.println("Ocurrió un error al consultar los contractor por skill " + e.getMessage());
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
                System.out.println("Error al consultar contractor pot experience " + e.getMessage());
                return contractorsByExperience;
            }
        }
 
        return contractorsByExperience;
    }
    
    
}
