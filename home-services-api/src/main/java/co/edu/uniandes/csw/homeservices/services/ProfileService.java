package co.edu.uniandes.csw.homeservices.services;

import co.edu.uniandes.csw.homeservices.api.IContractorLogic;
import co.edu.uniandes.csw.homeservices.api.ICustomerLogic;
import co.edu.uniandes.csw.homeservices.dtos.ProfileDTO;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import static co.edu.uniandes.csw.homeservices.services.UserService.getAccount;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import static co.edu.uniandes.csw.homeservices.services.UserService.getContractorId;
import static co.edu.uniandes.csw.homeservices.services.UserService.getCustomerId;
import javax.inject.Inject;

@Path("profile")
public class ProfileService {

    @Context
    private HttpServletRequest req;

    @Inject
    private ICustomerLogic customerLogic;
    @Inject
    private IContractorLogic contractorLogic;

    @GET
    public ProfileDTO getProfile() {
        ProfileDTO profile = new ProfileDTO(getAccount(req.getRemoteUser()));
        try {
            CustomerEntity customer = customerLogic.getCustomer(getCustomerId(req.getRemoteUser()));
            profile.setDocument(customer.getDocument());
            profile.setPicture(customer.getPicture());

        } catch (WebApplicationException e) {
        }
        try {
            ContractorEntity contractor = contractorLogic.getContractor(getContractorId(req.getRemoteUser()));
            profile.setDocument(contractor.getDocument());
            profile.setPicture(contractor.getPicture());
        } catch (WebApplicationException e) {
        }
        return profile;
    }
}