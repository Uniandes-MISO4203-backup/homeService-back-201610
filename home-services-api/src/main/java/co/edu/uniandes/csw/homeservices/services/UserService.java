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

public class UserService extends AuthService {

    private static final String CUSTOMER_CUSTOM_DATA_KEY = "customer_id";
    private static final String CONTRACTOR_CUSTOM_DATA_KEY = "contractor_id";
    private static final String CUSTOMER_GROUP_HREF = "https://api.stormpath.com/v1/groups/8qXFbgXBkzGQGmbdmDeL0";
    private static final String CONTRACTOR_GROUP_HREF = "https://api.stormpath.com/v1/groups/IW1qFyoN7BYbshFwFI4wo";

    @Inject
    private ICustomerLogic customerLogic;
    @Inject
    private IContractorLogic contractorLogic;

    @Override
    public void register(UserDTO user) {
        try {
            Account acc = createUser(user);
            for (Group group : acc.getGroups()) {
                switch (group.getHref()) {
                    case CUSTOMER_GROUP_HREF:
                        CustomerEntity customer = new CustomerEntity();
                        customer.setName(user.getGivenName());
                        customer.setLastName(user.getSurName());
                        customer = customerLogic.createCustomer(customer);
                        acc.getCustomData().put(CUSTOMER_CUSTOM_DATA_KEY, customer.getId());
                        break;
                    case CONTRACTOR_GROUP_HREF:
                        ContractorEntity contractor = new ContractorEntity();
                        contractor.setName(user.getGivenName());
                        contractor.setLastName(user.getSurName());
                        contractor = contractorLogic.createContractor(contractor);
                        acc.getCustomData().put(CONTRACTOR_CUSTOM_DATA_KEY, contractor.getId());
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
        Integer translatorId = (Integer) account.getCustomData().get(CONTRACTOR_CUSTOM_DATA_KEY);
        if (translatorId == null) {
            return null;
        }
        return new Long(translatorId);
    }

    public static Account getAccount(String href) {
        if (href == null) {
            return null;
        }
        return getClient().getResource(href, Account.class);
    }
}
