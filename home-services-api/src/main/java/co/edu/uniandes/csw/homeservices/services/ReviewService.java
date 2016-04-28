package co.edu.uniandes.csw.homeservices.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.homeservices.api.IReviewLogic;
import co.edu.uniandes.csw.homeservices.api.ICustomerLogic;
import co.edu.uniandes.csw.homeservices.api.IContractorLogic;
import co.edu.uniandes.csw.homeservices.dtos.ReviewDTO;
import co.edu.uniandes.csw.homeservices.entities.ReviewEntity;
import co.edu.uniandes.csw.homeservices.converters.ReviewConverter;
import static co.edu.uniandes.csw.homeservices.services.UserService.getCurrentCustomer;
import javax.servlet.http.HttpServletRequest;

/**
 * @generated
 */
@Path("/reviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReviewService {

    @Inject
    private IReviewLogic reviewLogic;
    @Inject
    private ICustomerLogic customerLogic;
    @Context
    private HttpServletRequest req;
    
    @Inject private IContractorLogic contractorLogic;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de ReviewDTO cada uno con sus
     * respectivos Review
     * @generated
     */
    @GET
    public List<ReviewDTO> getReviews() {
        return ReviewConverter.listEntity2DTO(reviewLogic.getReviews());
    }

    /**
     * Obtiene los datos de una instancia de Review a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ReviewDTO con los datos de la
     * instancia
     */
    @GET
    @Path("{id: \\d+}")
    public ReviewDTO getReview(@PathParam("id") Long id) {
        return ReviewConverter.basicEntity2DTO(reviewLogic.getReview(id));
    }
    
    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de ReviewDTO con los datos nuevos
     * @return Objeto de ReviewDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public ReviewDTO createReview(ReviewDTO dto) {
        ReviewEntity entity = ReviewConverter.basicDTO2Entity(dto);
        entity.setCustomer(getCurrentCustomer(req.getRemoteUser()));
        return ReviewConverter.basicEntity2DTO(reviewLogic.createReview(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de ReviewDTO con los nuevos datos.
     * @return Instancia de ReviewDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public ReviewDTO updateReview(@PathParam("id") Long id, ReviewDTO dto) {
        ReviewEntity entity = ReviewConverter.basicDTO2Entity(dto);
        entity.setId(id);
        return ReviewConverter.basicEntity2DTO(reviewLogic.updateReview(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteReview(@PathParam("id") Long id) {
        reviewLogic.deleteReview(id);
    }
}
