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
import co.edu.uniandes.csw.homeservices.api.ICategoryLogic;
import co.edu.uniandes.csw.homeservices.dtos.CategoryDTO;
import co.edu.uniandes.csw.homeservices.entities.CategoryEntity;
import co.edu.uniandes.csw.homeservices.converters.CategoryConverter;

/**
 * @generated
 */
@Path("/categorys")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryService {

    @Inject private ICategoryLogic categoryLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de CategoryDTO cada uno con sus respectivos Review
     * @generated
     */
    @GET
    public List<CategoryDTO> getCategorys() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", categoryLogic.countCategorys());
            return CategoryConverter.listEntity2DTO(categoryLogic.getCategorys(page, maxRecords));
        }
        return CategoryConverter.listEntity2DTO(categoryLogic.getCategorys());
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CategoryDTO con los datos del Book consultado y sus Review
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public CategoryDTO getCategory(@PathParam("id") Long id) {
        return CategoryConverter.fullEntity2DTO(categoryLogic.getCategory(id));
    }

    /**
     * Se encarga de crear un book en la base de datos.
     *
     * @param dto Objeto de CategoryDTO con los datos nuevos
     * @return Objeto de CategoryDTO con los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public CategoryDTO createCategory(CategoryDTO dto) {
        CategoryEntity entity = CategoryConverter.fullDTO2Entity(dto);
        return CategoryConverter.fullEntity2DTO(categoryLogic.createCategory(entity));
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de CategoryDTO con los nuevos datos.
     * @return Instancia de CategoryDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public CategoryDTO updateCategory(@PathParam("id") Long id, CategoryDTO dto) {
        CategoryEntity entity = CategoryConverter.fullDTO2Entity(dto);
        entity.setId(id);
        return CategoryConverter.fullEntity2DTO(categoryLogic.updateCategory(entity));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCategory(@PathParam("id") Long id) {
        categoryLogic.deleteCategory(id);
    }
}
