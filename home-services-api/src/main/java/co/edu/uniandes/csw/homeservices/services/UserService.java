/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.services;

/**
 *
 * @author Jhonatan Alarcón
 */
import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.service.AuthService;
import co.edu.uniandes.csw.homeservices.api.IContractorLogic;
import co.edu.uniandes.csw.homeservices.api.ICustomerLogic;
import co.edu.uniandes.csw.homeservices.converters.ContractorConverter;
import co.edu.uniandes.csw.homeservices.converters.CustomerConverter;
import co.edu.uniandes.csw.homeservices.dtos.ContractorDTO;
import co.edu.uniandes.csw.homeservices.dtos.CustomerDTO;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.directory.CustomData;
import com.stormpath.sdk.resource.ResourceException;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;


public class UserService extends AuthService {

    private static final String CUSTOMERID = "customer_id";
    private static final String CONTRACTORID = "contractor_id";
    private static final String CUSTOMER_ROLE = "customer";
    private static final String CONTRACTOR_ROLE = "contractor";

    @Inject
    private ICustomerLogic customerLogic;
    @Inject
    private IContractorLogic contractorLogic;

    @Override
    public void register(UserDTO user) {
        try {
            Account acc = createUser(user);
            CustomData customData = acc.getCustomData();
            if (user.getRoles().contains(CUSTOMER_ROLE)) {
                CustomerEntity entity = new CustomerEntity();
                entity.setName(user.getGivenName());
                entity.setLastName(user.getSurName());
                entity = customerLogic.createCustomer(entity);
                customData.put(CUSTOMERID, entity.getId().toString());
            }
            if (user.getRoles().contains(CONTRACTOR_ROLE)) {
                ContractorEntity entity = new ContractorEntity();
                entity.setName(user.getGivenName());
                entity.setLastName(user.getSurName());
                entity = contractorLogic.createContractor(entity);
                customData.put(CONTRACTORID, entity.getId().toString());
            }
            customData.save();
        } catch (ResourceException e) {
            throw new WebApplicationException(e, e.getStatus());
        }
    }

}
