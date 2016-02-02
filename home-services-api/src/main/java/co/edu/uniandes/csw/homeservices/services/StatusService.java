package co.edu.uniandes.csw.homeservices.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
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
import co.edu.uniandes.csw.homeservices.api.IStatusLogic;
import co.edu.uniandes.csw.homeservices.dtos.StatusDTO;
import co.edu.uniandes.csw.homeservices.entities.StatusEntity;
import co.edu.uniandes.csw.homeservices.converters.StatusConverter;

/**
 * @generated
 */
@Path("/statuss")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StatusService {

    @Inject private IStatusLogic statusLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de StatusDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<StatusDTO> getStatuss() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", statusLogic.countStatuss());
            return StatusConverter.listEntity2DTO(statusLogic.getStatuss(page, maxRecords));
        }
        return StatusConverter.listEntity2DTO(statusLogic.getStatuss());
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de StatusDTO con los datos del Book consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public StatusDTO getStatus(@PathParam("id") Long id) {
        return StatusConverter.fullEntity2DTO(statusLogic.getStatus(id));
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de StatusDTO con los datos nuevos
     * @return Objeto de StatusDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public StatusDTO createStatus(StatusDTO dto) {
        StatusEntity entity = StatusConverter.fullDTO2Entity(dto);
        return StatusConverter.fullEntity2DTO(statusLogic.createStatus(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de StatusDTO con los nuevos datos.
     * @return Instancia de StatusDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public StatusDTO updateStatus(@PathParam("id") Long id, StatusDTO dto) {
        StatusEntity entity = StatusConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return StatusConverter.fullEntity2DTO(statusLogic.updateStatus(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteStatus(@PathParam("id") Long id) {
        statusLogic.deleteStatus(id);
    }
}
