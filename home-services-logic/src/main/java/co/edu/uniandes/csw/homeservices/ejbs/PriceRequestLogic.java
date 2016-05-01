/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.ejbs;

import co.edu.uniandes.csw.homeservices.api.IContractorLogic;
import co.edu.uniandes.csw.homeservices.api.IPriceRequestLogic;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.entities.PriceRequestEntity;
import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import co.edu.uniandes.csw.homeservices.persistence.ContractorPersistence;
import co.edu.uniandes.csw.homeservices.persistence.PriceRequestPersistence;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author root
 */
@Stateless
public class PriceRequestLogic implements IPriceRequestLogic{
    
    @Inject 
    private PriceRequestPersistence persistence;
    @Inject 
    private ContractorPersistence persistence2;
    @Inject 
    private IContractorLogic contractorLogic;

    @Override
    public int countPriceRequests() {
       return persistence.count();
    }

    @Override
    public List<PriceRequestEntity> getPriceRequests() {
        return persistence.findAll();
    }

    @Override
    public List<PriceRequestEntity> getPriceRequests(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public PriceRequestEntity getPriceRequest(Long id) {
        return persistence.find(id);
    }

    @Override
    public PriceRequestEntity createPriceRequest(PriceRequestEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public PriceRequestEntity updatePriceRequest(PriceRequestEntity entity) {
        PriceRequestEntity newEntity = entity;
        PriceRequestEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deletePriceRequest(Long id) {
        persistence.delete(id);
    }

    @Override
    public List<PriceRequestEntity> getByContractor(Long idContractor) {
        return persistence.getByContractor(idContractor);
    }
    
    @Override
    public List<PriceRequestEntity> getByServiceRequest(Long customerId) {
        return persistence.getByServiceRequest(customerId);
    }
    
    @Override
    public void createPriceRequestByContractorId(Long contractorId) {
        ContractorEntity contractorEntity = contractorLogic.getContractor(Long.valueOf(contractorId));
        List<String> skills = persistence2.getSkillsByContractorId(contractorId.intValue());
        ServiceRequestEntity serviceRequestEntity = persistence2.getServiceRequestByContractorSkills(skills);
        if(serviceRequestEntity.getPriceRequestLimit().after(new Date())){
            PriceRequestEntity priceRequestEntity = new PriceRequestEntity();
            priceRequestEntity.setStatus("PENDIENTE");
            priceRequestEntity.setServiceRequest(serviceRequestEntity);
            priceRequestEntity.setContractor(contractorEntity);
            createPriceRequest(priceRequestEntity);
            //Sends an email to the contractor
            try {
              if(contractorEntity.getEmail()!=null){
                sendEmail(contractorEntity.getEmail(), contractorEntity.getName());
              }
            } catch (SendGridException ex) {
                
             }
        }
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
        
}
