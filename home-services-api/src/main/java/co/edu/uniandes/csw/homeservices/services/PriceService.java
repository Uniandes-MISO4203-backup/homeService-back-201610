/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.services;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.homeservices.api.IPriceLogic;
import co.edu.uniandes.csw.homeservices.converters.PriceConverter;
import co.edu.uniandes.csw.homeservices.dtos.PriceDTO;
import co.edu.uniandes.csw.homeservices.entities.PriceEntity;
import co.edu.uniandes.csw.homeservices.entities.PriceRequestEntity;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Implementa los servicios requeridos para la gestión de cotizaciones
 * juan camilo cerquera lozada<jc.cerquera10@uniandes.edu.co>
 */
@Path("/price")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PriceService {
    
    @Inject
    private IPriceLogic priceLogic;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest req;
    
    /**
     * Se encarga de crear una cotización en la base de datos.
     *
     * @param dto Objeto de PriceDTO con los datos nuevos
     * @param priceRequestId
     * @return Objeto de PriceDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public PriceDTO createPriceRequest(PriceDTO dto, @PathParam("priceRequestId") Long priceRequestId) {
        PriceEntity entity = PriceConverter.basicDTO2Entity(dto);
        PriceRequestEntity priceRequestEntity = new PriceRequestEntity();
        priceRequestEntity.setId(priceRequestId);
        entity.setPriceRequest(priceRequestEntity);
        return PriceConverter.basicEntity2DTO(priceLogic.createPrice(entity));
    }
}
