/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.homeservices.api.IReviewLogic;
import co.edu.uniandes.csw.homeservices.converters.ReviewConverter;
import co.edu.uniandes.csw.homeservices.dtos.ReviewDTO;
import co.edu.uniandes.csw.homeservices.entities.ReviewEntity;
import static co.edu.uniandes.csw.homeservices.services.UserService.getCurrentCustomer;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author LuisSebastian
 */
@Path("/customerReviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerReviewService {
    
    @Inject
    private IReviewLogic reviewLogic;
    @Context
    private HttpServletRequest req;
    
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
    
}
