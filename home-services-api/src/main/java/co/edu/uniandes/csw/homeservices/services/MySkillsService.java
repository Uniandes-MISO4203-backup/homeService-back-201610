package co.edu.uniandes.csw.homeservices.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.homeservices.api.IContractorLogic;
import co.edu.uniandes.csw.homeservices.converters.SkillConverter;
import co.edu.uniandes.csw.homeservices.dtos.SkillDTO;
import static co.edu.uniandes.csw.homeservices.services.UserService.getContractorId;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

@Path("myskills")
public class MySkillsService {

    @Inject
    private IContractorLogic contractorLogic;
    @Context
    private HttpServletRequest req;

    @GET
    public List<SkillDTO> getSkills() {
        Long id = getContractorId(req.getRemoteUser());
        return SkillConverter.listEntity2DTO(contractorLogic.listSkills(id));
    }

    @POST
    @StatusCreated
    @Path("{id: \\d+}")
    public SkillDTO createSkill(@PathParam("id") Long skillId) {
        Long contractorId = getContractorId(req.getRemoteUser());
        return SkillConverter.fullEntity2DTO(contractorLogic.addSkills(contractorId, skillId));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteSkill(@PathParam("id") Long skillId) {
        Long contractorId = getContractorId(req.getRemoteUser());
        contractorLogic.removeSkills(contractorId, skillId);
    }
}
