package co.edu.uniandes.csw.homeservices.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.homeservices.api.ICustomerLogic;
import co.edu.uniandes.csw.homeservices.api.IPriceRequestLogic;
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
import co.edu.uniandes.csw.homeservices.api.IServiceRequestLogic;
import co.edu.uniandes.csw.homeservices.converters.PriceListItemConverter;
import co.edu.uniandes.csw.homeservices.dtos.ServiceRequestDTO;
import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import co.edu.uniandes.csw.homeservices.converters.ServiceRequestConverter;
import co.edu.uniandes.csw.homeservices.dtos.SkillDTO;
import co.edu.uniandes.csw.homeservices.converters.SkillConverter;
import co.edu.uniandes.csw.homeservices.dtos.PriceListItemDTO;
import co.edu.uniandes.csw.homeservices.entities.CustomerEntity;
import static co.edu.uniandes.csw.homeservices.services.UserService.getCustomerId;
import javax.servlet.http.HttpServletRequest;

/**
 * @generated
 */
@Path("/serviceRequests")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServiceRequestService {

    @Inject
    private IServiceRequestLogic serviceRequestLogic;
    @Inject
    private IPriceRequestLogic priceRequestLogic;
    @Inject
    private ICustomerLogic customerLogic;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest req;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    @QueryParam("description")
    private String description;
    
    
    
    

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de ServiceRequestDTO cada uno con sus
     * respectivos Review
     * @generated
     */
    @GET
    public List<ServiceRequestDTO> getServiceRequests() {
        
        List<ServiceRequestDTO> list= null;
        if (description!=null){
            list= ServiceRequestConverter.listEntity2DTO(serviceRequestLogic.getByDescription(description, getCustomerId(req.getRemoteUser())));
        }
        else{
            list= ServiceRequestConverter.listEntity2DTO(customerLogic.listServiceRequests(getCustomerId(req.getRemoteUser())));
        }  
        return list;
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ServiceRequestDTO con los datos del Book consultado
     * y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public ServiceRequestDTO getServiceRequest(@PathParam("id") Long id) {
        return ServiceRequestConverter.fullEntity2DTO(customerLogic.addServiceRequests(getCustomerId(req.getRemoteUser()), id));
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de ServiceRequestDTO con los datos nuevos
     * @return Objeto de ServiceRequestDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public ServiceRequestDTO createServiceRequest(ServiceRequestDTO dto) {
        ServiceRequestEntity entity = ServiceRequestConverter.fullDTO2Entity(dto);
        CustomerEntity customer = new CustomerEntity();
        customer.setId(getCustomerId(req.getRemoteUser()));
        entity.setCustomer(customer);
        return ServiceRequestConverter.fullEntity2DTO(serviceRequestLogic.createServiceRequest(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de ServiceRequestDTO con los nuevos datos.
     * @return Instancia de ServiceRequestDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public ServiceRequestDTO updateServiceRequest(@PathParam("id") Long id, ServiceRequestDTO dto) {
        ServiceRequestEntity entity = ServiceRequestConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return ServiceRequestConverter.fullEntity2DTO(serviceRequestLogic.updateServiceRequest(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteServiceRequest(@PathParam("id") Long id) {
        serviceRequestLogic.deleteServiceRequest(id);
    }

    /**
     * Obtiene una colección de instancias de SkillDTO asociadas a una instancia
     * de ServiceRequest
     *
     * @param serviceRequestId Identificador de la instancia de ServiceRequest
     * @return Colección de instancias de SkillDTO asociadas a la instancia de
     * ServiceRequest
     * @generated
     */
    @GET
    @Path("{serviceRequestId: \\d+}/expectedskills")
    public List<SkillDTO> listExpectedskills(@PathParam("serviceRequestId") Long serviceRequestId) {
        return SkillConverter.listEntity2DTO(serviceRequestLogic.listExpectedskills(serviceRequestId));
    }
    
    @GET
    @Path("{serviceRequestId: \\d+}/pricelist")
    public List<PriceListItemDTO> getPriceList(@PathParam("serviceRequestId") Long serviceRequestId) {
        return PriceListItemConverter.listEntity2DTO(priceRequestLogic.getByServiceRequest(serviceRequestId));
    }
    /**
     * Obtiene una instancia de Skill asociada a una instancia de ServiceRequest
     *
     * @param serviceRequestId Identificador de la instancia de ServiceRequest
     * @param skillId Identificador de la instancia de Skill
     * @generated
     */
    @GET
    @Path("{serviceRequestId: \\d+}/expectedskills/{skillId: \\d+}")
    public SkillDTO getExpectedskills(@PathParam("serviceRequestId") Long serviceRequestId, @PathParam("skillId") Long skillId) {
        return SkillConverter.fullEntity2DTO(serviceRequestLogic.getExpectedskills(serviceRequestId, skillId));
    }

    /**
     * Asocia un Skill existente a un ServiceRequest
     *
     * @param serviceRequestId Identificador de la instancia de ServiceRequest
     * @param skillId Identificador de la instancia de Skill
     * @return Instancia de SkillDTO que fue asociada a ServiceRequest
     * @generated
     */
    @POST
    @Path("{serviceRequestId: \\d+}/expectedskills/{skillId: \\d+}")
    public SkillDTO addExpectedskills(@PathParam("serviceRequestId") Long serviceRequestId, @PathParam("skillId") Long skillId) {
        return SkillConverter.fullEntity2DTO(serviceRequestLogic.addExpectedskills(serviceRequestId, skillId));
    }

    /**
     * Remplaza las instancias de Skill asociadas a una instancia de
     * ServiceRequest
     *
     * @param serviceRequestId Identificador de la instancia de ServiceRequest
     * @param skills Colección de instancias de SkillDTO a asociar a instancia
     * de ServiceRequest
     * @return Nueva colección de SkillDTO asociada a la instancia de
     * ServiceRequest
     * @generated
     */
    @PUT
    @Path("{serviceRequestId: \\d+}/expectedskills")
    public List<SkillDTO> replaceExpectedskills(@PathParam("serviceRequestId") Long serviceRequestId, List<SkillDTO> skills) {
        return SkillConverter.listEntity2DTO(serviceRequestLogic.replaceExpectedskills(serviceRequestId, SkillConverter.listDTO2Entity(skills)));
    }

    /**
     * Desasocia un Skill existente de un ServiceRequest existente
     *
     * @param serviceRequestId Identificador de la instancia de ServiceRequest
     * @param skillId Identificador de la instancia de Skill
     * @generated
     */
    @DELETE
    @Path("{serviceRequestId: \\d+}/expectedskills/{skillId: \\d+}")
    public void removeExpectedskills(@PathParam("serviceRequestId") Long serviceRequestId, @PathParam("skillId") Long skillId) {
        serviceRequestLogic.removeExpectedskills(serviceRequestId, skillId);
    }
}
