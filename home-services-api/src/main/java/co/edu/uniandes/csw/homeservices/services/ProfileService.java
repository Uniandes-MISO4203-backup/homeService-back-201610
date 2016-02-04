package co.edu.uniandes.csw.homeservices.services;

import co.edu.uniandes.csw.homeservices.dtos.ProfileDTO;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import static co.edu.uniandes.csw.homeservices.services.UserService.getAccount;
import static co.edu.uniandes.csw.homeservices.services.UserService.getContractor;
import static co.edu.uniandes.csw.homeservices.services.UserService.getCustomer;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

@Path("profile")
public class ProfileService {

    @Context
    private HttpServletRequest req;

    @GET
    public ProfileDTO getProfile() {
        ProfileDTO profile = new ProfileDTO(getAccount(req.getRemoteUser()));
        try {
            CustomerEntity customer = getCustomer(req.getRemoteUser());
            profile.setDocument(customer.getDocument());
        } catch (WebApplicationException e) {
        }
        try {
            ContractorEntity contractor = getContractor(req.getRemoteUser());
            profile.setDocument(contractor.getDocument());
        } catch (WebApplicationException e) {
        }
        return profile;
    }
}
