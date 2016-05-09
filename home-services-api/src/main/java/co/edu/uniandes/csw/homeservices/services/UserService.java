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
import static co.edu.uniandes.csw.auth.stormpath.Utils.getClient;
import co.edu.uniandes.csw.homeservices.api.IContractorLogic;
import co.edu.uniandes.csw.homeservices.api.ICustomerLogic;
import co.edu.uniandes.csw.homeservices.entities.ContractorEntity;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.resource.ResourceException;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.servlet.http.HttpServletResponse;

public class UserService extends AuthService {

    private static final String CUSTOMER_CUSTOM_DATA_KEY = "customer_id";
    private static final String CONTRACTOR_CUSTOM_DATA_KEY = "contractor_id";
    private static final String CUSTOMER_GROUP_HREF = "https://api.stormpath.com/v1/groups/yAWNHHo2KKxlRy7mdmVY9";
    private static final String CONTRACTOR_GROUP_HREF = "https://api.stormpath.com/v1/groups/17sNxjYJEeYN8qDMfuBIbh";

    @Inject
    private ICustomerLogic customerLogic;
    @Inject
    private IContractorLogic contractorLogic;

    @Override
    public void register(UserDTO user) {
        try {
            Account acc = createUser(user);
            CustomerEntity customer = new CustomerEntity();
            ContractorEntity contractor = new ContractorEntity();
            for (Group group : acc.getGroups()) {
                switch (group.getHref()) {
                    case CUSTOMER_GROUP_HREF:
                        customer.setName(user.getGivenName());
                        customer.setLastName(user.getSurName());
                        customer = customerLogic.createCustomer(customer);
                        acc.getCustomData().put(CUSTOMER_CUSTOM_DATA_KEY, customer.getId());
                        break;
                    case CONTRACTOR_GROUP_HREF:
                        contractor.setName(user.getGivenName());
                        contractor.setLastName(user.getSurName());
                        contractor = contractorLogic.createContractor(contractor);
                        acc.getCustomData().put(CONTRACTOR_CUSTOM_DATA_KEY, contractor.getId());
                        break;
                    default:
                        break;
                }
            }
            acc.getCustomData().save();
        } catch (ResourceException e) {
            throw new WebApplicationException(e, e.getStatus());
        }
    }

    public static Long getCustomerId(String href) {
        Account account = getAccount(href);
        if (account == null) {
            return null;
        }
        Integer customerId = (Integer) account.getCustomData().get(CUSTOMER_CUSTOM_DATA_KEY);
        if (customerId == null) {
            return null;
        }
        return new Long(customerId);
    }

    public static Long getContractorId(String href) {
        Account account = getAccount(href);
        if (account == null) {
            return null;
        }
        Integer contractorId = (Integer) account.getCustomData().get(CONTRACTOR_CUSTOM_DATA_KEY);
        if (contractorId == null) {
            return null;
        }
        return new Long(contractorId);
    }

    public static Account getAccount(String href) {
        if (href == null) {
            return null;
        }
        return getClient().getResource(href, Account.class);
    }
    
    
    
    public static CustomerEntity getCurrentCustomer(String href) {
        Account account = getAccount(href);
        Integer customerId = (Integer) account.getCustomData().get(CUSTOMER_CUSTOM_DATA_KEY);
        if (customerId == null) {
            throw new WebApplicationException(HttpServletResponse.SC_FORBIDDEN);
        }
        CustomerEntity customer = new CustomerEntity();
        customer.setId(new Long(customerId));
        return customer;
    }
}
