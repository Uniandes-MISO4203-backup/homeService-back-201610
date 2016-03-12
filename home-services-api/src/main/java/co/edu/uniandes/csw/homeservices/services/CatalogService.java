package co.edu.uniandes.csw.homeservices.services;

import co.edu.uniandes.csw.homeservices.api.ICustomerLogic;
import co.edu.uniandes.csw.homeservices.api.IServiceRequestLogic;
import co.edu.uniandes.csw.homeservices.converters.ServiceRequestConverter;
import co.edu.uniandes.csw.homeservices.dtos.ServiceRequestDTO;
import static co.edu.uniandes.csw.homeservices.services.UserService.getCustomerId;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("catalog")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CatalogService {

    @Inject 
    private IServiceRequestLogic serviceRequestLogic;
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


    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de ServiceRequestDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<ServiceRequestDTO> getServiceRequests() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", serviceRequestLogic.countServiceRequests());
            return ServiceRequestConverter.listEntity2DTO(customerLogic.listServiceRequests(getCustomerId(req.getRemoteUser())  ));
          
          //  this.response.setIntHeader("X-Total-Count", serviceRequestLogic.countServiceRequests());
          //  return ServiceRequestConverter.listEntity2DTO(serviceRequestLogic.getServiceRequests(page, maxRecords));
        }
        //return ServiceRequestConverter.listEntity2DTO(serviceRequestLogic.getServiceRequests());
       return ServiceRequestConverter.listEntity2DTO(customerLogic.listServiceRequests(getCustomerId(req.getRemoteUser())  ));

    }
}
    
