package co.edu.uniandes.csw.homeservices.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.homeservices.dtos.CategoryDTO;
import co.edu.uniandes.csw.homeservices.services.CategoryService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class CategoryTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String categoryPath = "categorys";
    private final static List<CategoryDTO> oraculo = new ArrayList<>();
    private WebTarget target;
    private final String apiPath = "api";
    private final String username = System.getenv("USERNAME_USER");
    private final String password = System.getenv("PASSWORD_USER");

    @ArquillianResource
    private URL deploymentURL;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.csw.homeservices:home-services-logic:0.1.0")
                        .withTransitivity().asFile())
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.csw:auth-utils:0.1.0")
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(CategoryService.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() {
        ClientConfig config = new ClientConfig();
        config.register(LoggingFilter.class);
        return ClientBuilder.newClient(config).target(deploymentURL.toString()).path(apiPath);
    }

    @BeforeClass
    public static void setUp() {
        insertData();
    }

    public static void insertData() {
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            CategoryDTO category = factory.manufacturePojo(CategoryDTO.class);
            category.setId(i + 1L);

            oraculo.add(category);

        }
    }

    public Cookie login(String username, String password) {
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);
        user.setRememberMe(true);
        Response response = target.path("users").path("login").request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Ok) {
            return response.getCookies().get(JWT.cookieName);
        } else {
            return null;
        }
    }

    @Before
    public void setUpTest() {
        target = createWebTarget();
    }

    @Test
    @InSequence(1)
    public void createCategoryTest() throws IOException {
        CategoryDTO category = oraculo.get(0);
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(categoryPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(category, MediaType.APPLICATION_JSON));
        CategoryDTO  categoryTest = (CategoryDTO) response.readEntity(CategoryDTO.class);
        Assert.assertEquals(category.getId(), categoryTest.getId());
        Assert.assertEquals(category.getName(), categoryTest.getName());
        Assert.assertEquals(category.getDescription(), categoryTest.getDescription());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getCategoryById() {
        Cookie cookieSessionId = login(username, password);
        CategoryDTO categoryTest = target.path(categoryPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(CategoryDTO.class);
        Assert.assertEquals(categoryTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(categoryTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(categoryTest.getDescription(), oraculo.get(0).getDescription());
    }

    @Test
    @InSequence(3)
    public void listCategoryTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(categoryPath)
                .request().cookie(cookieSessionId).get();
        String listCategory = response.readEntity(String.class);
        List<CategoryDTO> listCategoryTest = new ObjectMapper().readValue(listCategory, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listCategoryTest.size());
    }

    @Test
    @InSequence(4)
    public void updateCategoryTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        CategoryDTO category = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CategoryDTO categoryChanged = factory.manufacturePojo(CategoryDTO.class);
        category.setName(categoryChanged.getName());
        category.setDescription(categoryChanged.getDescription());
        Response response = target.path(categoryPath).path(category.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(category, MediaType.APPLICATION_JSON));
        CategoryDTO categoryTest = (CategoryDTO) response.readEntity(CategoryDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(category.getName(), categoryTest.getName());
        Assert.assertEquals(category.getDescription(), categoryTest.getDescription());
    }

    @Test
    @InSequence(5)
    public void deleteCategoryTest() {
        Cookie cookieSessionId = login(username, password);
        CategoryDTO category = oraculo.get(0);
        Response response = target.path(categoryPath).path(category.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
