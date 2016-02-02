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
import co.edu.uniandes.csw.homeservices.api.ISkillLogic;
import co.edu.uniandes.csw.homeservices.dtos.SkillDTO;
import co.edu.uniandes.csw.homeservices.entities.SkillEntity;
import co.edu.uniandes.csw.homeservices.converters.SkillConverter;

/**
 * @generated
 */
@Path("/skills")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SkillService {

    @Inject private ISkillLogic skillLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de SkillDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<SkillDTO> getSkills() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", skillLogic.countSkills());
            return SkillConverter.listEntity2DTO(skillLogic.getSkills(page, maxRecords));
        }
        return SkillConverter.listEntity2DTO(skillLogic.getSkills());
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de SkillDTO con los datos del Book consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public SkillDTO getSkill(@PathParam("id") Long id) {
        return SkillConverter.fullEntity2DTO(skillLogic.getSkill(id));
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de SkillDTO con los datos nuevos
     * @return Objeto de SkillDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public SkillDTO createSkill(SkillDTO dto) {
        SkillEntity entity = SkillConverter.fullDTO2Entity(dto);
        return SkillConverter.fullEntity2DTO(skillLogic.createSkill(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de SkillDTO con los nuevos datos.
     * @return Instancia de SkillDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public SkillDTO updateSkill(@PathParam("id") Long id, SkillDTO dto) {
        SkillEntity entity = SkillConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return SkillConverter.fullEntity2DTO(skillLogic.updateSkill(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSkill(@PathParam("id") Long id) {
        skillLogic.deleteSkill(id);
    }
}
