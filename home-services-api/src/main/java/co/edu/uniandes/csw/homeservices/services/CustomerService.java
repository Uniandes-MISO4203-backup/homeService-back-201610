package co.edu.uniandes.csw.homeservices.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import static co.edu.uniandes.csw.auth.stormpath.Utils.getClient;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.homeservices.api.ICustomerLogic;
import co.edu.uniandes.csw.homeservices.dtos.CustomerDTO;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import co.edu.uniandes.csw.homeservices.converters.CustomerConverter;
import co.edu.uniandes.csw.homeservices.dtos.ServiceRequestDTO;
import co.edu.uniandes.csw.homeservices.converters.ServiceRequestConverter;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 * @generated
 */
@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerService {
    private static final String CUSTOMER_GROUP_HREF = "https://api.stormpath.com/v1/groups/yAWNHHo2KKxlRy7mdmVY9";
    private static final String ADMIN_GROUP_HREF = "https://api.stormpath.com/v1/groups/rRAbN1pw2hLLj66xeAx4z";

    @Inject private ICustomerLogic customerLogic;
    @Context private HttpServletRequest req;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de CustomerDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<CustomerDTO> getCustomers() {
        String accountHref = req.getRemoteUser();
        if (accountHref != null) {
            Account account = getClient().getResource(accountHref, Account.class);
            for (Group gr : account.getGroups()) {
                switch (gr.getHref()) {                    
                    case ADMIN_GROUP_HREF:
                        if (page != null && maxRecords != null) {
                        this.response.setIntHeader("X-Total-Count", customerLogic.countCustomers());
                        return CustomerConverter.listEntity2DTO(customerLogic.getCustomers(page, maxRecords));
                    }
                        return CustomerConverter.listEntity2DTO(customerLogic.getCustomers());
                    case CUSTOMER_GROUP_HREF:
                        Integer id = (int) account.getCustomData().get("customer_id");
                        List<CustomerDTO> list = new ArrayList();
                        list.add(CustomerConverter.fullEntity2DTO(customerLogic.getCustomer(id.longValue())));
                        return list;    
                }
            }
        }
        return null;        
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CustomerDTO con los datos del Book consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public CustomerDTO getCustomer(@PathParam("id") Long id) {
        return CustomerConverter.fullEntity2DTO(customerLogic.getCustomer(id));
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de CustomerDTO con los datos nuevos
     * @return Objeto de CustomerDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public CustomerDTO createCustomer(CustomerDTO dto) {
        CustomerEntity entity = CustomerConverter.fullDTO2Entity(dto);
        return CustomerConverter.fullEntity2DTO(customerLogic.createCustomer(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de CustomerDTO con los nuevos datos.
     * @return Instancia de CustomerDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public CustomerDTO updateCustomer(@PathParam("id") Long id, CustomerDTO dto) {
        CustomerEntity entity = CustomerConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return CustomerConverter.fullEntity2DTO(customerLogic.updateCustomer(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCustomer(@PathParam("id") Long id) {
        customerLogic.deleteCustomer(id);
    }

    /**
     * Obtiene una colección de instancias de ServiceRequestDTO asociadas a una
     * instancia de Customer
     *
     * @param customerId Identificador de la instancia de Customer
     * @return Colección de instancias de ServiceRequestDTO asociadas a la instancia de Customer
     * @generated
     */
    @GET
    @Path("{customerId: \\d+}/serviceRequests")
    public List<ServiceRequestDTO> listServiceRequests(@PathParam("customerId") Long customerId) {
        return ServiceRequestConverter.listEntity2DTO(customerLogic.listServiceRequests(customerId));
    }

    /**
     * Obtiene una instancia de ServiceRequest asociada a una instancia de Customer
     *
     * @param customerId Identificador de la instancia de Customer
     * @param serviceRequestId Identificador de la instancia de ServiceRequest
     * @generated
     */
    @GET
    @Path("{customerId: \\d+}/serviceRequests/{serviceRequestId: \\d+}")
    public ServiceRequestDTO getServiceRequests(@PathParam("customerId") Long customerId, @PathParam("serviceRequestId") Long serviceRequestId) {
        return ServiceRequestConverter.fullEntity2DTO(customerLogic.getServiceRequests(customerId, serviceRequestId));
    }

    /**
     * Asocia un ServiceRequest existente a un Customer
     *
     * @param customerId Identificador de la instancia de Customer
     * @param serviceRequestId Identificador de la instancia de ServiceRequest
     * @return Instancia de ServiceRequestDTO que fue asociada a Customer
     * @generated
     */
    @POST
    @Path("{customerId: \\d+}/serviceRequests/{serviceRequestId: \\d+}")
    public ServiceRequestDTO addServiceRequests(@PathParam("customerId") Long customerId, @PathParam("serviceRequestId") Long serviceRequestId) {
        return ServiceRequestConverter.fullEntity2DTO(customerLogic.addServiceRequests(customerId, serviceRequestId));
    }

    /**
     * Remplaza las instancias de ServiceRequest asociadas a una instancia de Customer
     *
     * @param customerId Identificador de la instancia de Customer
     * @param serviceRequests Colección de instancias de ServiceRequestDTO a asociar a instancia de Customer
     * @return Nueva colección de ServiceRequestDTO asociada a la instancia de Customer
     * @generated
     */
    @PUT
    @Path("{customerId: \\d+}/serviceRequests")
    public List<ServiceRequestDTO> replaceServiceRequests(@PathParam("customerId") Long customerId, List<ServiceRequestDTO> serviceRequests) {
        return ServiceRequestConverter.listEntity2DTO(customerLogic.replaceServiceRequests(customerId, ServiceRequestConverter.listDTO2Entity(serviceRequests)));
    }

    /**
     * Desasocia un ServiceRequest existente de un Customer existente
     *
     * @param customerId Identificador de la instancia de Customer
     * @param serviceRequestId Identificador de la instancia de ServiceRequest
     * @generated
     */
    @DELETE
    @Path("{customerId: \\d+}/serviceRequests/{serviceRequestId: \\d+}")
    public void removeServiceRequests(@PathParam("customerId") Long customerId, @PathParam("serviceRequestId") Long serviceRequestId) {
        customerLogic.removeServiceRequests(customerId, serviceRequestId);
    }
}
