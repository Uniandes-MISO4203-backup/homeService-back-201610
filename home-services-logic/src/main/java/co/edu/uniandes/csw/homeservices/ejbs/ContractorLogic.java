package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.IContractorLogic;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.entities.PriceRequestEntity;
import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import co.edu.uniandes.csw.homeservices.persistence.ContractorPersistence;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import co.edu.uniandes.csw.homeservices.persistence.PriceRequestPersistence;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ContractorLogic implements IContractorLogic {

    @Inject private ContractorPersistence persistence;
    @Inject private PriceRequestPersistence priceRequestPersistence;

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
    
    /**
     * Metodo que permite realizar la busqueda de los contractors dado un 
     * skill en string
     * 
     * @param skill 
     * @return List<ContractorEntity> encontrados que tienen ese skill 
     *         dentro de sus skill, o lista vacia si no encuentra nada
     */
    @Override
    public List<ContractorEntity> getContractorsBySkill(String skill) {
        
        List<ContractorEntity> contractorsBySkill = persistence.getContractorsBySkill(skill);
        
        return contractorsBySkill;
    }

    @Override
    public List<ContractorEntity> getContractorsByExperience(String experience) {
        List<ContractorEntity> contractorsByExperience = persistence.getContractorsByExperience(experience);  
        return contractorsByExperience;
    }

    @Override
    public List<ContractorEntity> getContractorsBySkillServiceReq(int serviceReqId) {
        List<ContractorEntity> contractorsByExperience = persistence.getContractorsBySkillServiceReq(serviceReqId);  
        return contractorsByExperience;
    }

    @Override
    public List<ContractorEntity> getContractorsBySkillServiceReqAndCreatePriceRequest(int contractorId) {
        ContractorEntity contractorEntity = getContractor(Long.valueOf(contractorId));
        List<String> skills = persistence.getSkillsByContractorId(contractorId);
        ServiceRequestEntity serviceRequestEntity = persistence.getServiceRequestByContractorSkills(skills);
        if(serviceRequestEntity.getPriceRequestLimit().after(new Date())){
            PriceRequestEntity priceRequestEntity = new PriceRequestEntity();
            priceRequestEntity.setStatus("PENDIENTE");
            priceRequestEntity.setServiceRequest(serviceRequestEntity);
            priceRequestEntity.setContractor(contractorEntity);
            priceRequestPersistence.create(priceRequestEntity);
            //Sends an email to the contractor
            try {
              if(contractorEntity.getEmail()!=null){
                sendEmail(contractorEntity.getEmail(), contractorEntity.getName());
              }
            } catch (SendGridException ex) {
                Logger.getLogger(ContractorLogic.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        List<ContractorEntity> contractorsByExperience = persistence.getContractorsBySkillServiceReq(serviceRequestEntity.getId().intValue());  
        return contractorsByExperience;
    }
    
    /**
     * Send an email
     * @param emailTo
     * @param nameTo
     * @throws SendGridException 
     */
     public static void sendEmail(String emailTo, String nameTo) throws SendGridException {
        SendGrid sendgrid = new SendGrid("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");
        SendGrid.Email email = new SendGrid.Email();
        email.addTo(emailTo);
        email.setFrom("cotizaciones@homeservices.com");
        email.setSubject("[HomeServices]Solicitud cotización");
        email.setHtml("Hola "+nameTo+"!\n Tienes una nueva solitud de cotización.");
        SendGrid.Response response = sendgrid.send(email);
    }
     
         /**
     * Send an email
     * @param emailTo
     * @param nameTo
     * @throws SendGridException 
     */
     public static void sendEmail2(String emailTo, String nameTo, char indicador) throws SendGridException {
         
        SendGrid sendgrid = new SendGrid("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");
        SendGrid.Email email = new SendGrid.Email();
        email.addTo(emailTo);
        email.setFrom("cotizaciones@homeservices.com");
        
        if (indicador == 'C') {
            email.setSubject("[HomeServices] Solicitud Contratada");
            email.setHtml("Hola "+nameTo+"!\n Tu cotización fue CONTRATADA. ");
        }
        if (indicador == 'R') {
            email.setSubject("[HomeServices] Solicitud Rechazada");
            email.setHtml("Hola "+nameTo+"!\n Tu cotización fue RECHAZADA. ");
        }
        
        SendGrid.Response response = sendgrid.send(email);
    }
}
