/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.services;

import co.edu.uniandes.csw.homeservices.api.IPriceRequestLogic;
import co.edu.uniandes.csw.homeservices.converters.PriceRequestConverter;
import co.edu.uniandes.csw.homeservices.dtos.PriceRequestDTO;
import co.edu.uniandes.csw.homeservices.entities.PriceRequestEntity;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Implementa los servicios requeridos para la gestión de solicitudes de
 * cotización.
 *
 * @author juan camilo cerquera lozada <jc.cerquera10@uniandes.edu.co>
 */
@Path("/priceRequests")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PriceRequestService {

    @Inject
    private IPriceRequestLogic priceRequestLogic;
    @Context
    private HttpServletResponse response;
    @Context
    private HttpServletRequest req;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;

    /**
     * Obtiene las solicitudes de cotización asignadas a un contratista.
     */
    @GET
    public List<PriceRequestDTO> getPriceRequestByContractor() {
        List<PriceRequestDTO> list = PriceRequestConverter.listEntity2DTO(priceRequestLogic.getByContractor(UserService.getContractorId(req.getRemoteUser())));
        return list;
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de PriceRequest a modificar
     * @param dto Instancia de PriceRequestDTO con los nuevos datos.
     * @return Instancia de PriceRequestDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public PriceRequestDTO updatePriceRequest(@PathParam("id") Long id, PriceRequestDTO dto) {
        PriceRequestEntity entity = PriceRequestConverter.basicDTO2Entity(dto);
        entity.setId(id);
        return PriceRequestConverter.basicEntity2DTO(priceRequestLogic.updatePriceRequest(entity));
    }
    
    /**
     * Crea un nuevo PriceRequest
     * @param contractorId 
     */
    @POST
    @Path("{contractorId: \\d+}")
    public void addSkills(@PathParam("contractorId") Long contractorId) {
        System.out.println("Entro con: "+String.valueOf(contractorId));
        priceRequestLogic.createPriceRequestByContractorId(contractorId);       
    }

}
