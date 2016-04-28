package co.edu.uniandes.csw.homeservices.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.homeservices.dtos.CustomerDTO;
import co.edu.uniandes.csw.homeservices.dtos.ReviewDTO;
import co.edu.uniandes.csw.homeservices.services.ReviewService;
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
public class ReviewTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String reviewsPath = "reviews";
    private final static List<ReviewDTO> oraculo = new ArrayList<>();
    private final String customerPath = "customers";
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
                .addPackage(ReviewService.class.getPackage())
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
            ReviewDTO review = factory.manufacturePojo(ReviewDTO.class);
            review.setId(i + 1L);

            oraculo.add(review);

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
    public void createReviewsTest() throws IOException {
                PodamFactory factory = new PodamFactoryImpl();
        CustomerDTO customer = factory.manufacturePojo(CustomerDTO.class);;
        Cookie cookieSessionId = login(username, password);
        target.path(customerPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON));
        
        ReviewDTO review = oraculo.get(0);
        
        Response response = target.path(reviewsPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(review, MediaType.APPLICATION_JSON));
        
        ReviewDTO reviewTest = (ReviewDTO) response.readEntity(ReviewDTO.class);
        Assert.assertEquals(review.getId(), reviewTest.getId());
        Assert.assertEquals(review.getName(), reviewTest.getName());
        Assert.assertEquals(review.getDescription(), reviewTest.getDescription());
        Assert.assertEquals(review.getSource(), reviewTest.getSource());
        Assert.assertEquals(review.getValue(), reviewTest.getValue());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getReviewsById() {
        Cookie cookieSessionId = login(username, password);
        ReviewDTO reviewTest = target.path(reviewsPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(ReviewDTO.class);
        Assert.assertEquals(reviewTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(reviewTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(reviewTest.getDescription(), oraculo.get(0).getDescription());
        Assert.assertEquals(reviewTest.getSource(), oraculo.get(0).getSource());
        Assert.assertEquals(reviewTest.getValue(), oraculo.get(0).getValue());
    }

    @Test
    @InSequence(3)
    public void listReviewsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(reviewsPath)
                .request().cookie(cookieSessionId).get();
        String listReviews = response.readEntity(String.class);
        List<ReviewDTO> listReviewsTest = new ObjectMapper().readValue(listReviews, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listReviewsTest.size());
    }

    @Test
    @InSequence(4)
    public void updateTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ReviewDTO review = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReviewDTO reviewChanged = factory.manufacturePojo(ReviewDTO.class);
        review.setName(reviewChanged.getName());
        review.setDescription(reviewChanged.getDescription());
        review.setValue(reviewChanged.getValue());
        review.setSource(reviewChanged.getSource());
        Response response = target.path(reviewsPath).path(review.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(review, MediaType.APPLICATION_JSON));
        ReviewDTO statusTest = (ReviewDTO) response.readEntity(ReviewDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(review.getName(), reviewChanged.getName());
        Assert.assertEquals(review.getDescription(), reviewChanged.getDescription());
        Assert.assertEquals(review.getSource(), reviewChanged.getSource());
        Assert.assertEquals(review.getValue(), reviewChanged.getValue());
    }

    @Test
    @InSequence(5)
    public void deleteTest() {
        Cookie cookieSessionId = login(username, password);
        ReviewDTO status = oraculo.get(0);
        Response response = target.path(reviewsPath).path(status.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
